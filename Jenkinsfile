pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME' // Nombre del Maven configurado en Jenkins
    }

    environment {
        GIT_REPO = 'https://github.com/AlexanderLozano17/CI-Jenkins-Springboot.git'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'credencial_GitHub',
                url: "${GIT_REPO}", 
                branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Report') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        success {
            echo '✅ Build completado con éxito.'
        }
        failure {
            echo '❌ Build fallido.'
        }
    }
}
