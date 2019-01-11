#!/usr/bin/env bash

# docker-compose up -d --build

kubectl apply -f user-db-config-map.yml --record
kubectl apply -f user-db-volume-claim.yml --record
kubectl apply -f user-db-deployment.yml --record
kubectl apply -f user-db-service.yml --record

kubectl apply -f user-deployment.yml --record
kubectl apply -f user-service.yml --record

kubectl get deployment
kubectl get pods
kubectl get services

URL=$(minikube service user-service --url)

echo "$URL"

# getopts is an utility that allows you to add parameters (e.g. -v).
while getopts ":v:b" opt; do
    case $opt in
        v)
            RESPONSE=$(curl -d '{"id":"1", "dotaId":"2", "username":"testaaa", "password": "papapapapa13", "showDotaMatches": false}' \
            -H "Content-Type: application/json" -X POST http://192.168.99.104:31675/api/v1/user)

            echo "$RESPONSE"

            if [[ $RESPONSE == *"500 Internal Server Error"* ]]; then
                FIRSTPOD=$(kubectl get pods | grep 'user-deployment' | head -n 1 | awk '{print $1;}')
                kubectl logs ${FIRSTPOD}
            fi
            ;;
        b)
            if which open > /dev/null
                then
                  open "${URL}/healthcheck"
                fi
            ;;
        \?)
            echo "Invalid option: -$OPTARG."
            echo "-v: Test if postgresql database is up."
            echo "-b: Open healthcheck with browser."
            ;;
    esac
done