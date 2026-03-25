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
                bat 'dir'
            }
        }

        stage('Docker Compose Down') {
            steps {
                bat 'docker compose down'
            }
        }

        stage('Docker Compose Build Up') {
            steps {
                bat 'docker compose up -d --build'
            }
        }

        stage('Check Running Containers') {
            steps {
                bat 'docker compose ps'
            }
        }
    }
}