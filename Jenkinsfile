pipeline{

    agent any

    stages{

        stage('Build Jar'){

            steps{

                sh "mvn clean package -DskipTests"
            }

        }

        stage('Build Image'){

            steps{

               sh "docker build -t ashwinborkar79/testjar ."
            }

        }

        stage('Push Image'){

            environment {
                DOCKER_HUB = credentials('dockerhub-credentials')
            }

            steps{
                sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}' //use single quotes only
                sh "docker push ashwinborkar79/testjar"
                  }

                }
        }

    post {
        always {
            sh "docker logout"
        }
    }
}