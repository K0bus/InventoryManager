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
                sh 'mvn clean package -Dbuild.number=${BUILD_NUMBER}'
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
    post {
        always {
            withCredentials([string(credentialsId: 'DISCORD_URL_INVENTORYMANAGER', variable: 'DISCORD_URL')]) {
                discordSend webhookURL: DISCORD_URL,
                title: "$JOB_NAME #$BUILD_NUMBER",
                thumbnail: "https://i.imgur.com/iJdYF4k.png",
                description: "CI Automated in Jenkins",
                footer: "by K0bus",
                successful: true,
                link: env.BUILD_URL,
                showChangeset: true,
                result: currentBuild.currentResult
            }
        }
    }
}
