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
                sh 'docker build -t FirstRepository/product:latest .'
            }
        }
        stage('Maven Deploy'){
            steps{
                echo "Deploying the jar file into server"
            }
        }
    }
}
