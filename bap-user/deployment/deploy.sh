#!/usr/bin/env bash

# Exit immediately if a command exits with a non-zero status. But seems like we don't even need this.
set -e

MINIKUBE_STATUS=$(minikube status)

echo "Minikube Status"
echo "---------------"
echo "$MINIKUBE_STATUS"
echo ""

if [[ $MINIKUBE_STATUS == *"Error"* ]] || [[ $MINIKUBE_STATUS == *"Stopped"* ]] || [[ $MINIKUBE_STATUS != *"Running"* ]]; then
    echo "There seems to be an issue with minikube or it is not currently running. Attempting to start minikube..."
    echo ""
    minikube stop
    minikube start
    echo ""
fi

echo "Building docker images..."
docker-compose up -d --build
echo ""

echo "Replacing user-deployment..."
kubectl apply --force -f user-deployment.yml
echo ""

echo "Replacing user-db-deployment..."
kubectl apply --force -f user-db-deployment.yml
echo ""

echo "Replacing user-db-config-map..."
kubectl apply --force -f user-db-config-map.yml
echo ""

echo "Replacing user-service..."
kubectl apply --force -f user-service.yml
echo ""

echo "Replacing user-db-service..."
kubectl apply --force -f user-db-service.yml
echo ""

echo "Replacing user-db-volume-claim..."
kubectl apply --force -f user-db-volume-claim.yml
echo ""

echo "Deployment"
echo "----------"
kubectl get deployment
echo ""

echo "Pods"
echo "----"
kubectl get pods
echo ""

echo "Services"
echo "--------"
kubectl get services
echo ""

URL=$(minikube service user-service --url)

echo "User service URL"
echo "$URL"
echo ""

RETRIES_MS=10
while STATUS=$(curl -Is ${URL}/healthcheck | head -n 1) && [[ "$STATUS" != *"200"* ]]; do
    # Seems like ${STATUS} returns nothing
#    echo "${STATUS}"

    PODS_STATUS=$(kubectl get pods)

    if [[ $PODS_STATUS == *"ImagePullBackOff"* ]] || [[ $PODS_STATUS == *"ErrImagePull"* ]]; then
        echo "There seems to be an error creating an image."
        echo ""
        # TODO: Only describe pods that failed, not all of them
        kubectl describe pod
        echo ""
        echo "Exiting..."
        echo ""

        exit 1
    fi

    echo "${URL}/healthcheck is not returning 200... reattempting to retry in ${RETRIES_MS} seconds..."
    sleep "${RETRIES_MS}s"
done

# getopts is an utility that allows you to add parameters (e.g. -v).
while getopts ":vb" opt; do
    case $opt in
        (v)
            echo "Creating new user..."

            RESPONSE=$(curl -d '{"id":"1", "dotaId":"2", "username":"testaaa", "password": "papapapapa13", "showDotaMatches": false}' \
            -H "Content-Type: application/json" -X POST ${URL}/api/v1/user)

            echo "$RESPONSE"

            if [[ $RESPONSE == *"500 Internal Server Error"* ]]; then
                FIRSTPOD=$(kubectl get pods | grep 'user-deployment' | head -n 1 | awk '{print $1;}')
                kubectl logs ${FIRSTPOD}
            fi
            ;;
        (b)
            if which open > /dev/null
                then
                  open "${URL}/healthcheck"
                fi
            ;;
        (\?)
            echo "Invalid option: -$OPTARG."
            echo "-v: Test if postgresql database is up."
            echo "-b: Open healthcheck with browser."
            ;;
    esac
done