pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'docker build -t springtest:latest .'
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
                sh 'docker stop spring-test'
                sh 'docker run --name spring-test --detached --rm -p 8081:8081 springtest'
                sh 'docker ps'
            }
        }
    }
}