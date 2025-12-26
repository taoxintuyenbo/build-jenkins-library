def call(){
  stage('Push Snapshot'){
     withCredentials([usernamePassword(credentialsId:  "${NEXUS_CRED}", usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
        sh "${MAVEN_HOME}/bin/mvn clean deploy -Dmaven.test.skip=true -Dusername=${NEXUS_USER} -Dpassword=${NEXUS_PASS} -X"
    }
  }
}
