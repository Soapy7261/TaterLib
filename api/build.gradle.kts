base {
    archivesName = "taterapi"
}

dependencies {
    // Configurate
    implementation("org.spongepowered:configurate-hocon:4.2.0-SNAPSHOT")
    implementation("io.leangen.geantyref:geantyref:1.3.16")

    // bStats
    implementation("org.bstats:bstats-base:3.0.2")
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("org.bstats:bstats-bungeecord:3.0.2")
    implementation("org.bstats:bstats-sponge:3.0.2")
    implementation("org.bstats:bstats-velocity:3.0.2")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // MySQL
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("com.mysql:mysql-connector-j:8.3.0")

    // Brigadier
    implementation(libs.brigadier)

    // Mixin
    implementation(libs.mixin)
    implementation(libs.asm.tree)

    // Cross API dependencies
    implementation("net.luckperms:api:5.4")
    implementation("me.lucko:spark-api:0.1-SNAPSHOT")

    // Tooling
    compileOnly(project(":modapi:entrypoint-spoof"))
    implementation(variantOf(libs.modapi.crossperms) {
        classifier("downgraded-8")
    })
    implementation(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8")
    })
}

java.disableAutoTargetJvm()
