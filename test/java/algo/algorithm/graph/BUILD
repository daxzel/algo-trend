package(
    default_visibility = ["//src/test:__subpackages__"],
)

java_test(
    name = "graph_tests",
    srcs = glob(["*.java"]),
    use_testrunner = False,
    main_class = "org.junit.platform.console.ConsoleLauncher",
    args = ["--select-package", "algo.algorithm.graph"],
    deps = [
        "//src/java/algo/algorithm/graph:graph",
        "//src/java/algo/datastructures:datastructures",
        "//test/java/algo/utils:utils",

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
