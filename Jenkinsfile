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

        stage('Dockerize') {
            steps {
                bat "docker build -f Dockerfile -t football-standing ."
            }
        }

        stage("Deploy") {
            steps {
                bat "docker run -p 8080:8080 -e \"SPRING_PROFILES_ACTIVE=${params.SPRING_PROFILE}\" football-standing"
            }
        }
    }
}
