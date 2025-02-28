pipeline {
    agent any
    tools {
        maven "3.9.9"
        jdk "openjdk-23.0.2"
    }
    options {
        disableConcurrentBuilds()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -Dbuild.number=${BUILD_NUMBER}'
            }
        }
//         stage('Unit Test') {
//             steps {
//                 sh 'mvn test'
//             }
//             post {
//                 always {
//                     junit '**/target/surefire-reports/*.xml' // Publie les résultats des tests
//                 }
//             }
//         }
//         stage('Code Coverage Analysis') {
//             steps {
//                 sh 'mvn jacoco:prepare-agent test jacoco:report'
//             }
//             post {
//                 success {
//                     publishHTML(target: [
//                         reportDir: 'target/site/jacoco',
//                         reportFiles: 'index.html',
//                         reportName: 'JaCoCo Coverage Report'
//                     ])
//                 }
//             }
//         }
        stage('Static Code Analysis (SpotBugs)') {
            steps {
                sh 'mvn spotbugs:check || true' // Évite l'échec du build si des warnings sont détectés
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/spotbugsXml.xml'
                }
            }
        }
        stage('Archive Artifacts') {
            steps {
                sh 'rm -rf artifacts'
                sh 'mkdir artifacts'
                sh 'cp target/InventoryManager*.jar artifacts/'
                archiveArtifacts artifacts: 'artifacts/*.jar', followSymlinks: false
            }
        }
    }
}
