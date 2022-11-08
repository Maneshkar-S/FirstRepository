pipeline{
    agent any
    stages{
        stage('Git Clone'){
            steps{
                git 'git branch: "deploybranch", url: "https://github.com/Maneshkar-S/FirstRepository.git"'
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
                sh 'rm -rf dockerimages'
                sh 'mkdir dockerimages'
                sh 'cd dockerimages'
                sh 'cp /var/jenkins_home/workspace/Package_Job/target/product-0.0.1-SNAPSHOT.war .'
                sh 'echo "password" | sudo -S docker build -t tomcatwebserver:1.0 .'
            }
        }
        stage('Deploy Docker Image'){
            steps{
                echo "Deploying the war file into server..."
                sh 'sudo docker run -itd --name tomcatwebserver -p 8888:8080 tomcatwebserver:1.0'
            }
        }
    }
}
