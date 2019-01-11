#!/usr/bin/env bash

kubectl delete configmap user-db-config-map
kubectl delete pvc user-db-pv-claim
kubectl delete deployment user-db-deployment
kubectl delete service user-db-service

kubectl delete deployment user-deployment
kubectl delete service user-service