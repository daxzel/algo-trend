workspace(name = "algo-trend")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "2.5"

RULES_JVM_EXTERNAL_SHA = "249e8129914be6d987ca57754516be35a14ea866c616041ff0cd32ea94d2f3a1"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "com.google.guava:guava:26.0-jre",
    ],
    fetch_sources = True,
    repositories = [
        "http://uk.maven.org/maven2",
        "https://jcenter.bintray.com/",
    ],
)

load(":junit5.bzl", "junit_jupiter_java_repositories", "junit_platform_java_repositories")

JUNIT_JUPITER_VERSION = "5.7.1"

JUNIT_PLATFORM_VERSION = "1.7.1"

junit_jupiter_java_repositories(
    version = JUNIT_JUPITER_VERSION,
)

junit_platform_java_repositories(
    version = JUNIT_PLATFORM_VERSION,
)
