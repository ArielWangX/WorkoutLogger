package com.arielwang.workoutlogger.testutils

/**
 * Invoke this method to throw and error when your method is not being called by a suspend fun.
 *
 * This is usually used to prevent the usage of shared memory to communicate with code under
 * test in coroutines tests. [Communicating with shared memory is a bad idea](https://go.dev/blog/codelab-share).
 *
 * Concrete example:
 *
 * ```
 * fun takeLastScreen(): Screen {
 *   assertCallingContextIsNotSuspended()
 *
 *   return screens.takeValue()
 * }
 *
 * @Test
 * fun myTest() = runBlocking {
 *   assertCallingContextIsNotSuspended() // fine
 *   takeLastScreen() // boom!
 * }
 * ```
 */
fun assertCallingContextIsNotSuspended() {
    val stackTrace = Thread.currentThread().stackTrace
    if (stackTrace.getOrNull(3)?.methodName == "invokeSuspend") {
        error("Calling context is suspending; use a suspending method instead")
    }
}
