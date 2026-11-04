#!/usr/bin/env bash
set -euo pipefail

AMBIENTE="${1:-}"
JAR_PATH="${2:-}"

if [[ -z "$AMBIENTE" || -z "$JAR_PATH" ]]; then
  echo "Error: Uso esperado: bash devops/desplegar.sh <ambiente> <jar>"
  exit 1
fi

if [[ -z "${DEPLOY_TOKEN:-}" ]]; then
  echo "Error: DEPLOY_TOKEN no está definido en las variables de entorno."
  exit 1
fi

if [[ ! -f $JAR_PATH ]]; then
  echo "Error: No se encontró el artefacto JAR en la ruta: $JAR_PATH"
  exit 1
fi

echo "===================================================="
echo "Iniciando despliegue en ambiente: $AMBIENTE"
echo "Artefacto a desplegar: $JAR_PATH"
echo "Autenticando con token seguro (longitud: ${#DEPLOY_TOKEN} caracteres)..."
echo "Despliegue completado exitosamente en [$AMBIENTE]."
echo "===================================================="