pipeline {
    agent {
        docker {
            image 'openjdk:11-jdk'
            args '--network jenkins -e DOCKER_HOST=tcp://localhost:2375'
        }
    }
    options {
        timestamps()
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                docker.build("springtest:latest")
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