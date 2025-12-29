def call(){
  stage('Push Snapshot'){
    def GIT_HASH = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
    withCredentials([usernamePassword(credentialsId: "${NEXUS_CRED}", usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
      sh """
        curl -v -u ${NEXUS_USER}:${NEXUS_PASS} --upload-file ${ARTIFACT_PATH} \
          http://${NEXUS_URL}/repository/maven-snapshots/${NEXUS_GROUP}/${NEXUS_ARTIFACT_ID}/${VERSION}/${NEXUS_ARTIFACT_ID}-${VERSION}-SNAPSHOTS.jar
      """
    }
  }
}
