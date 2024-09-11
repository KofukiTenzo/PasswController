pipeline {
    agent any
  
    stages {
        stage('Docker Build') {
            steps {
                echo 'Building Image'
                sh 'docker build -t passwc .'
            }
        }
        
        stage('Push to Dockerhub') {
            steps {
                echo 'Push build image to Dockerhub'
                withCredentials([usernamePassword(credentialsId: "DockerHubAcc", passwordVariable: "DockerHubPass", usernameVariable: "DockerHubUser")]){
                    sh '''
                    docker login -u $DockerHubUser -p $DockerHubPass
                    docker tag passwc:latest $DockerHubUser/passwc:latest
                    docker push $DockerHubUser/passwc:latest
                    '''
                }
            }
        }

        stage('Update Docker Image') {
            steps {
                echo 'Updating passwc image on latest'
                sh 'docker pull kofuki/passwc:latest'
            }
        }

        stage('Stop and remove old container') {
            steps {
                echo 'Stoping old passwc container'
                sh 'docker stop passwc'

                echo 'Removing old passwc container'
                sh 'docker rm passwc'
            }
        }

        stage('Deploy from DockerHub') {
            steps {
                echo 'Containering from DockerHub'
                sh 'docker run --name passwc --restart always -d --cpus="1.0" -p 8000:8000 kofuki/passwc:latest'
            }
        }
        
    }
}
