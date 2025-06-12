pipeline {

    agent any

    stages {

        stage('Build Jar') {
            steps {
                echo 'Building JAR file...'
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                echo 'Building Docker image...'
                bat "docker build -t ashwinborkar79/testjar:latest ."
            }
        }

        stage('Push Image') {
            environment {
                DOCKER_CREDENTIALS_ID = credentials('docker-hub-creds')
                // this is the ID of the Docker Hub credentials stored in Jenkins
            }
            steps {
                echo 'Logging in and pushing Docker image...'
                bat "docker login -u %DOCKER_CREDENTIALS_ID_USR% -p %DOCKER_CREDENTIALS_ID_PSW%"
                bat "docker push ashwinborkar79/testjar:latest"
                bat "docker tag ashwinborkar79/testjar:latest ashwinborkar79/testjar:${env.BUILD_NUMBER}"
                bat "docker push ashwinborkar79/testjar:${env.BUILD_NUMBER}"
            }
        }
    }
        post {
            always {
                echo 'Logging out...'
                bat "docker logout"
            }
        }
    }
