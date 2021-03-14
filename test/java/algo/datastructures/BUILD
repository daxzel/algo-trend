package(
    default_visibility = ["//src/test:__subpackages__"],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "datastructures_tests",
    srcs = glob([
        "*.java",
    ]),
    test_package = "algo.datastructures",
    deps = [
        "//src/java/algo/datastructures:datastructures",
    ],
)