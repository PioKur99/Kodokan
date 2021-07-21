pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'docker build -t kodokan-springboot:latest .'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying....'
                    def backendRunningCount = sh(script: "docker ps -q -f name=kodokan-backend | grep -c kodokan-backend", returnStdout: true) as Integer
                    if( backendRunningCount != 0){
                        sh 'docker stop kodokan-backend'
                    }
                    sh 'docker run --name kodokan-backend --detach --rm -p 8081:8081 kodokan-springboot'
                    sh 'docker ps'
                }
            }
        }
    }
}