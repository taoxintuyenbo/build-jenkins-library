def call(String toolFile = 'tools.properties') {
    if (!fileExists(toolFile)) {
        error "Tools properties file not found: ${toolFile}"
    }

    // Read the properties file
    def toolsConfig = readProperties(file: toolFile)

    // Iterate through the keys and values in the properties file
    toolsConfig.each { key, value ->
        // Use the value to get the tool home
        def toolHome = tool(value.trim())
        env["${key.trim().toUpperCase()}_HOME"] = toolHome
        echo "Loaded tool: ${key.trim()} = ${value.trim()}"
    }
}
