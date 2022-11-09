pipeline{
    agent any
    stages{
        stage('Git Clone'){
            steps{
                git 'https://github.com/Maneshkar-S/FirstRepository.git'
            }
        }
        stage('Maven Test'){
            steps{
                sh 'mvn test'
            }
        }
        stage('Maven Build'){
            steps{
                sh 'mvn package'
            }
        }
        stage('Create Docker Image'){
            steps{
                sh 'echo "password" | sudo -S sudo docker stop tomcatwebserver'
                sh 'echo "password" | sudo -S sudo docker rm tomcatwebserver'
                sh 'echo "password" | sudo -S sudo docker rmi tomcatwebserver:1.0'
                sh 'rm -rf dockerimages'
                sh 'mkdir dockerimages'
                sh 'cd dockerimages'
                sh 'cp /var/jenkins_home/workspace/MultiBranch_Pipeline_Job_feature/target/product-0.0.1-SNAPSHOT.war .'
                sh 'echo "password" | sudo -S docker build -t tomcatwebserver:1.0 .'
            }
        }
        stage('Deploy Docker Image'){
            steps{
                echo "Deploying the war file into server..."
                sh 'echo "password" | sudo -S docker run -itd --name tomcatwebserver -p 8888:8080 tomcatwebserver:1.0'
            }
        }
    }
}
