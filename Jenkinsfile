pipeline {
    agent {
            docker {
                image 'maven:3.8.1-adoptopenjdk-16'
                args '-v /root/.m2:/root/.m2'
            }
        }

    stages {
        stage("Build") {
            steps{
                sh "mvn -version"
                sh "mvn clean install"
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
