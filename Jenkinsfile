node {
    stage('Checkout') {
        checkout scm
    }

    stage('Show Files') {
        sh 'ls -al'
    }

    stage('Docker Compose Down') {
        sh 'docker-compose -p study-app down'
    }

    stage('Docker Compose Build Up') {
        sh 'docker-compose -p study-app up -d --build'
    }

    stage('Check Running Containers') {
        sh 'docker-compose -p study-app ps'
    }

    stage('Health Check') {
        steps {
            sh 'sleep 11'
            sh 'curl -f http://localhost:8080/health'
        }
    }
}