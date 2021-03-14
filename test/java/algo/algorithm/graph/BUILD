package(
    default_visibility = ["//src/test:__subpackages__"],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "graph_tests",
    srcs = glob([
        "*.java",
    ]),
    test_package = "algo.algorithm.graph",
    deps = [
        "//src/java/algo/algorithm/graph:graph",
        "//src/java/algo/datastructures:datastructures",
        "//test/java/algo/utils:utils",
    ],
)