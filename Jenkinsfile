pipeline {
    agent any
    tools {
        maven "3.9.9"
    }
    options {
        disableConcurrentBuilds()
    }
    stages {
        stage('Build') {
            steps {
                env.JAVA_HOME="${tool 'jdk1.8.0_111'}"
                env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
                sh 'java -version'
                sh 'mvn clean install'
            }
        }
        stage('Archive artifacts') {
            steps {
                sh 'rm -rf artifacts'
                sh 'mkdir artifacts'
                sh 'cp target/InventoryManager*.jar artifacts/'
                archiveArtifacts artifacts: 'artifacts/*.jar', followSymlinks: false
            }
        }
        stage('Fingerprint artifacts') {
            steps {
                fingerprint 'worldedit-bukkit/build/libs/FastAsyncWorldEdit*.jar'
            }
        }
        stage('Publish JUnit test results') {
            steps {
                junit 'worldedit-core/build/test-results/test/*.xml,worldedit-bukkit/build/test-results/test/*.xml'
            }
        }
    }
}