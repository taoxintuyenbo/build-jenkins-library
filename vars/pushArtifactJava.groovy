def call() {
  stage('Push artifact to Nexus Repo'){
    if(env.BRANCH_NAME == 'releases'){
        withCredentials([usernamePassword(credentialsId: "${NEXUS_CRED}", variable: 'NEXUS_ACC')]) {
          sh """curl -v -u ${NEXUS_ACC} --upload-file ${ARTIFACT_PATH} \
            http://${NEXUS_URL}/repository/${NEXUS_RELEASES_REPO}/${NEXUS_GROUP}/${NEXUS_ARTIFACT_ID}/${env.BRANCH_NAME}/${env.VERSION}-${env.BRANCH_NAME}.jar
          """
        }
    }
    if(env.BRANCH_NAME.startsWith('uat')){
        def GIT_HASH = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
        withCredentials([usernamePassword(credentialsId: "${NEXUS_CRED}", variable: 'NEXUS_ACC')]) {
          sh """curl -v -u ${NEXUS_ACC} --upload-file ${ARTIFACT_PATH} \
            http://${NEXUS_URL}/repository/${NEXUS_RELEASES_REPO}/${NEXUS_GROUP}/${NEXUS_ARTIFACT_ID}/${env.BRANCH_NAME}/${env.VERSION}-${env.BRANCH_NAME}-${GIT_HASH}.jar
          """
        }
    }
  }
}
