def call(){
  stage('Compile'){
    sh "${MAVEN_HOME}/bin/mvn compile"
  }
}
