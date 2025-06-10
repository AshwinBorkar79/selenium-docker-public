pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                echo 'Building JAR file...'
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image'){
            steps{
                echo 'Building Docker image...'
               bat "docker build -t ashwinborkar79/testjar ."
            }
        }

        stage('Push Image'){
            steps{
                echo 'Pushing Docker image to Docker Hub...'
                bat "docker push ashwinborkar79/testjar"
                  }
            }
        }
}