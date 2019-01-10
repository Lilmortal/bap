#!/usr/bin/env bash

kubectl delete deployment user-deployment
kubectl delete service user-service

kubectl create -f user-deployment.yml
kubectl create -f user-service.yml

kubectl get deployment
kubectl get pods
kubectl get services

minikube service user-service --url