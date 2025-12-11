def call() {
  stage('Build'){
    sh "${MAVEN_HOME}/bin/mvn package"
  }
}
