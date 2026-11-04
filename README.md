# CuotaFacil

## Revisión de integración continua y despliegue

| Aspecto | Cómo se resolvió | Correcto (Sí/No) | Corrección |
|---|---|---|---|
| Disparadores de `ci.yml` | `push` a `feature/**` y `pull_request` hacia `master`. | Sí | — |
| Permisos y timeout | `permissions: contents: read`. `ci.yml` tiene `timeout-minutes: 15`; `cicd.yml` tiene timeout en `build`, `deploy-dev` y `deploy-cert`. | Sí | — |
| Ejecución Maven | `./mvnw -B -ntp verify` en GitHub Actions y `mvn -B -ntp verify` en Jenkins. No aparecen `-DskipTests` ni `-DskipITs`. | Sí | — |
| JaCoCo y reportes | `jacoco-maven-plugin` `0.8.12`, validación `LINE`/`COVEREDRATIO` con mínimo `0.80`. Los reportes Surefire, Failsafe y JaCoCo se suben con `if: always()`. | Sí | — |
| Jenkinsfile declarativo | Pipeline declarativo con las cuatro etapas, en orden: Compilar, Pruebas y cobertura, Publicar artefacto y Desplegar a cert. | Sí | — |
| `when` y `withCredentials` | Despliegue condicionado a `master` mediante `when { branch 'master' }`; credencial `cuotafacil-deploy-token` inyectada como `DEPLOY_TOKEN` usando `withCredentials`. | Sí | — |
| `deploy-dev` y `deploy-cert` | Ambos jobs descargan `cuotafacil-jar` con `actions/download-artifact@v4` y ejecutan el script sobre el JAR descargado. No recompilan. | Sí | — |
| `DEPLOY_TOKEN` | GitHub Actions lo obtiene desde `${{ secrets.DEPLOY_TOKEN }}` y Jenkins desde la credencial segura `cuotafacil-deploy-token`. No está escrito en archivos. | Sí | — |
| Versión Maven | `pom.xml` usa `<version>1.0.0</version>`, sin `-SNAPSHOT`. | Sí | — |

