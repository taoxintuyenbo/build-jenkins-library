def call(String toolFile = "./tools.txt") {
    if (!fileExists(toolFile)) {
        error "Tools file not found: ${toolFile}"
    }

    def toolsConfig = readFile(file: toolFile)
                      .split("\n")
                      .findAll { it.trim() && !it.startsWith("#") }

    toolsConfig.each { line ->
        def (key, value) = line.split("=")
        def toolHome = tool(value.trim())
        // Export env variable: MAVEN_HOME, JDK_HOME, SONAR_HOME...
        env["${key.trim().toUpperCase()}_HOME"] = toolHome
        echo "Loaded tool: ${key.trim()} = ${value.trim()}"
    }
}
