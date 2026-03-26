node {
    stage('Checkout') {
        checkout scm
    }

    stage('Show Files') {
        sh 'ls -al'
    }

    stage('Docker Compose Down') {
        sh 'docker-compose down'
    }

    stage('Docker Compose Build Up') {
        sh 'docker-compose up -d --build'
    }

    stage('Check Running Containers') {
        sh 'docker-compose ps'
    }
}