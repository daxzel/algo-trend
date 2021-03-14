package(
    default_visibility = ["//src/test:__subpackages__"],
)

load("//:junit5.bzl", "java_junit5_test")

# Use java_test after bazel officially support junit5
java_junit5_test(
    name = "lamport_test",
    srcs = glob([
        "LamportClocksTestCase.java",
    ]),
    test_package = "algo.algorithm.distributed",
    deps = [
        "//src/java/algo/algorithm/distributed:distributed",
    ],
)