pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Show Files') {
            steps {
                sh 'ls -al'
            }
        }

        stage('Docker Compose Down') {
            steps {
                sh 'docker-compose down'
            }
        }

        stage('Docker Compose Build Up') {
            steps {
                sh 'docker-compose up -d --build'
            }
        }

        stage('Check Running Containers') {
            steps {
                sh 'docker-compose ps'
            }
        }
    }
}