package(
    default_visibility = ["//src/test:__subpackages__"],
)

java_test(
    name = "java_tests",
    srcs = glob(["*.java"]),
    use_testrunner = False,
    main_class = "org.junit.platform.console.ConsoleLauncher",
    args = ["--select-package", "algo.java"],
    deps = [
        # JUnit Jupiter
        "@maven//:org_junit_jupiter_junit_jupiter_api",
        "@maven//:org_junit_jupiter_junit_jupiter_engine",
        "@maven//:org_junit_jupiter_junit_jupiter_params",

        # JUnit Platform
        "@maven//:org_junit_platform_junit_platform_console",
        "@maven//:org_junit_platform_junit_platform_commons",
        "@maven//:org_junit_platform_junit_platform_engine",
        "@maven//:org_junit_platform_junit_platform_launcher",
        "@maven//:org_junit_platform_junit_platform_suite_api",

        # Runtime deps
        "@maven//:org_apiguardian_apiguardian_api",
        "@maven//:org_opentest4j_opentest4j",
    ],
)
