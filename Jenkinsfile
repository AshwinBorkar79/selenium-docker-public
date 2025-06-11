pipeline{

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
                bat "docker build -t ashwinborkar79/testjar ."
            }
        }

        stage('Push Image') {
            environment {
                DOCKER_CREDENTIALS_ID = credentials('docker-hub-creds')
                // this is the ID of the Docker Hub credentials stored in Jenkins
            }
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                bat "docker login -u ${DOCKER_CREDENTIALS_ID_USR} -p ${DOCKER_CREDENTIALS_ID_PSW}"
                bat "docker push ashwinborkar79/testjar"
            }
        }
    }
    post{
        always {
            echo 'Logging out...'
            bat "docker logout"
        }
    }
}