pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    options {
        timeout(time: 20, unit: 'MINUTES')
    }

    stages {
        stage('Compilar') {
            steps {
                sh 'mvn -B -ntp compile'
            }
        }

        stage('Pruebas y cobertura') {
            steps {
                sh 'mvn -B -ntp verify'
                junit testResults: 'target/surefire-reports/*.xml,target/failsafe-reports/*.xml',
                      allowEmptyResults: false
            }
        }

        stage('Publicar artefacto') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Desplegar a cert') {
            when {
                branch 'master'
            }
            steps {
                withCredentials([string(credentialsId: 'cuotafacil-deploy-token', variable: 'DEPLOY_TOKEN')]) {
                    sh 'bash devops/desplegar.sh cert target/cuotafacil-*.jar'
                }
            }
        }
    }
}
