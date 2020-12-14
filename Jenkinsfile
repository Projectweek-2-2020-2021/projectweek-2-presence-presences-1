pipeline {
    agent {
        label 'docker'
    }
    environment {
        PROJECT_NAME = env.GIT_URL.replaceFirst(/^.*\/(?:projectweek-2-)?([^\/]+?).git$/, '$1')
    }
    stages {
        stage('Build Docker Image for TEST and deploy to TEST'){
            steps{
               sh 'cp -R /home/jenkins/.m2 ${WORKSPACE}/.m2'
               sh 'docker build -t ${PROJECT_NAME}:dev .'
               sh 'docker service create -p 15000:8080 --name ${PROJECT_NAME}_DEV  ${PROJECT_NAME}:dev || docker service update --image ${PROJECT_NAME}:dev ${PROJECT_NAME}_DEV'
               // The port 150xx in "-p 150xx:8080" has to be changed at the beginning of the week. This will be communicated.
               sh 'echo "The TEST site is located at https://dev_${PROJECT_NAME}.project2.projectweek.be or http://dev_${PROJECT_NAME}.project2.projectweek.be:150xx"'
               sh 'echo "Waiting a few seconds for the TEST server to spin-up, so the tests can run against an up and running TEST site"'
               sh 'sleep 30'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build War File'){
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: '**/*.war'
            }
        }
        stage('Build Docker Image for PROD'){
            steps{
               sh 'docker build -t ${PROJECT_NAME}:prod .'
            }
        }
        stage('Deployment to PROD') {
            steps {
                sh 'docker service create -p 14000:8080 --name ${PROJECT_NAME}_PROD  ${PROJECT_NAME}:prod || docker service update --image ${PROJECT_NAME}:prod ${PROJECT_NAME}_PROD'
                 // The port 140xx in "-p 140xx:8080" has to be changed at the beginning of the week. This will be communicated.
                sh 'echo "The PRODUCTION site is located at https://${PROJECT_NAME}.project2.projectweek.be or http://${PROJECT_NAME}.project2.projectweek.be:140xx"'
            }
        }
    }
    post {
        always {
                junit 'target/surefire-reports/*.xml'
        }
    }
}
