pipeline {
    agent any

    environment {
        mavenHome = tool 'jenkins-maven'
    }

    tools {
        jdk 'java-17'
    }

    parameters {
        choice(name: 'SPRING_PROFILE', choices: ['dev', 'prod'], description: 'Select the Spring profile')
    }

    stages {

        stage('Build') {
            steps {
                bat "mvn clean install -DskipTests"
            }
        }

        stage('Test') {
            steps {
                bat "mvn test"
            }
        }

        stage('Deploy') {
            steps {
                bat "mvn jar:jar deploy:deploy -P${params.SPRING_PROFILE}"
            }
        }
    }
}
