def call() {
  stage('Build Docker images'){
    if(env.BRANCH_NAME == 'releases'){
      sh "docker build -t ${NEXUS_URL_DOCKER}/${NEXUS_ARTIFACT_ID}:${VERSION}-${env.BRANCH_NAME} ."
    }
    if(env.BRANCH_NAME.startWith('uat')){
      def GIT_HASH = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
      sh "docker build -t ${NEXUS_URL_DOCKER}/${NEXUS_ARTIFACT_ID}:${VERSION}-${env.BRANCH_NAME}-${GIT_HASH} ."
    }
  }
}
