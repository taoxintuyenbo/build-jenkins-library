def call(){
  stage('Push Snapshot'){
    sh "${MAVEN_HOME}/bin/mvn clean deploy -Dmaven.test.skip=true"
  }
}
