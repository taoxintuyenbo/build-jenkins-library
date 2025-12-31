def call(){
  stage('Push Docker Image to Nexus'){
    if(env.BRANCH_NAME == 'releases'){
      withCredentials([usernamePassword(credentialsId: "${NEXUS_CRED}", usernameVariable: 'NEXUS_ACC', passwordVariable: 'NEXUS_PASS')]){
        sh "echo $NEXUS_PASS | docker login -u $NEXUS_ACC --password-stdin ${NEXUS_URL_DOCKER}"
        sh "docker push ${NEXUS_URL_DOCKER}/docker-releases:${VERSION}-${env.BRANCH_NAME}"
      }
    }
    if(env.BRANCH_NAME.startsWith('uat')){
      def GIT_HASH = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
      withCredentials([usernamePassword(credentialsId: "${NEXUS_CRED}", usernameVariable: 'NEXUS_ACC', passwordVariable: 'NEXUS_PASS')]){
        sh "echo $NEXUS_PASS | docker login -u $NEXUS_ACC --password-stdin ${NEXUS_URL_DOCKER}"
        sh "docker push ${NEXUS_URL_DOCKER}/docker-releases:${VERSION}-${env.BRANCH_NAME}-${GIT_HASH}"
      }
    }
  }
}
