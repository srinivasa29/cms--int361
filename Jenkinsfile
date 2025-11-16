pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven-3.9'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git credentialsId: 'github-cred',
                    url: 'https://github.com/srinivasa29/cms--int361.git',
                    branch: 'main'
            }
        }

        stage('Build Maven Project') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t smartcrm-app:latest .'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh '''
                    docker rm -f smartcontactcrm || true
                    docker rm -f smartcrm-mysql || true

                    docker compose down || true
                    docker compose up -d --build
                '''
            }
        }
    }
}
