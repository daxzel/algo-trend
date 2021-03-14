package(
    default_visibility = ["//src/test:__subpackages__"],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "java_tests",
    srcs = glob([
        "*.java",
    ]),
    test_package = "algo.java",
)