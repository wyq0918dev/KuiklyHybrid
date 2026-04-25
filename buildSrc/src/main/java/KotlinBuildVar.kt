object KuiklyVersion {

    private const val KUIKLY_VERSION = "2.17.0"
    private const val KOTLIN_VERSION = "2.1.21"

    /**
     * 获取 Kuikly 版本号，版本号规则：${shortVersion}-${kotlinVersion}
     * 适用于 core、core-ksp、core-annotation、core-render-android
     */
    fun getKuiklyVersion(): String {
        return "$KUIKLY_VERSION-$KOTLIN_VERSION"
    }
}

object BuildPlugin {
    val gradle by lazy { "com.tencent.kuikly-open:core-gradle-plugin:${KuiklyVersion.getKuiklyVersion()}" }
    val render by lazy { "com.tencent.kuikly-open:core-render-android:${KuiklyVersion.getKuiklyVersion()}" }
    val core by lazy { "com.tencent.kuikly-open:core:${KuiklyVersion.getKuiklyVersion()}" }
    val annotations by lazy { "com.tencent.kuikly-open:core-annotations:${KuiklyVersion.getKuiklyVersion()}" }
    val ksp by lazy { "com.tencent.kuikly-open:core-ksp:${KuiklyVersion.getKuiklyVersion()}" }
}