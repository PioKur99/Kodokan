pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'docker build -t springtest -f Dockerfile'
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
                sh 'docker run -d -p 8081:8080 springtest'
                sh 'docker ps'
            }
        }
    }
}