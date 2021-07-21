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
                    echo 'Deploying....'
                    sh 'docker container stop ${docker ps -q -f name=kodokan-backend}'
                    sh 'docker run --name kodokan-backend --detach --rm -p 8081:8081 kodokan-springboot'
                    sh 'docker ps'
            }
        }
    }
}