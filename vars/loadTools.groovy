def call(String toolFile = 'tools.properties') {
    if (!fileExists(toolFile)) {
        error "Tools properties file not found: ${toolFile}"
    }

    def toolsConfig = readProperties(file: toolFile)

    toolsConfig.each { key, value ->
        def toolHome = tool(value.trim())
        env["${key.trim().toUpperCase()}_HOME"] = toolHome
    }
}
