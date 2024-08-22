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
                    docker login -u ${env.DockerHubUser} -p ${env.DockerHubPass}
                    docker tag passwc:latest ${env.DockerHubUser}/passwc:latest
                    docker push ${env.DockerHubUser}/passwc:latest
                    '''
                }
            }
        }
    }
}
