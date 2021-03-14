package(
    default_visibility = ["//src/test:__subpackages__"],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "puzzles_tests",
    srcs = glob([
        "*.java",
    ]),
    test_package = "algo.puzzles",
    deps = [
        "//src/java/algo/puzzles:puzzles",
        "//src/java/algo/datastructures:datastructures",
        "//src/java/algo/datastructures/tree:tree",
    ],
)