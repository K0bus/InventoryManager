pipeline {
    agent {
        docker {
            image "maven:3.9.9-ibm-semeru-21-jammy"
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -version'
                sh 'mvn clean install -B -Dbuild.number=${BUILD_NUMBER}'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }

    /*post {
        always {
            archiveArtifacts artifacts: 'target/InventoryManager*.jar', fingerprint: true
            discordSend webhookURL: params.DISCORD_URL,
                title: "$JOB_NAME #$BUILD_NUMBER",
                thumbnail: "https://i.imgur.com/iJdYF4k.png",
                description: "CI Automated in Jenkins",
                footer: "by K0bus",
                successful: true,
                link: env.BUILD_URL,
                showChangeset: true,
                result: currentBuild.currentResult
        }
    }*/
}