package com.arielwang.workoutlogger.testutils

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.withTimeout
import java.lang.Exception

/**
 * A channel with a tiny API intended for use in testing.
 *
 * [AwaitChannel] is a channel with [UNLIMITED] capacity that can be sent values without
 * blocking or suspending, and can be suspendfully or non-suspendfully polled.
 */
class AwaitChannel<T>(
    /** [name] is used to produce more informative error messages. */
    val name: String? = null
) {
    private val channel = Channel<T>(capacity = UNLIMITED)

    /**
     * Send a value to this channel.
     */
    fun add(value: T) { channel.trySend(value) }

    val isEmpty: Boolean get() { return channel.isEmpty }

    fun assertEmpty() {
        check(isEmpty) {
            "Unconsumed events: ${takeCurrentContents()}"
        }
    }

    fun asChannel(): Channel<T> = channel
}

/**
 * Wait for a value on this channel for 1s, throwing a [CancellationException] if none arrives.
 */
suspend fun <T> AwaitChannel<T>.awaitValue(name: String? = this.name) = try {
    withTimeout(1000L) {
        asChannel().receive()
    }
} catch (e: TimeoutCancellationException) {
    error(name?.let { "No value produced for $it in 1000ms" } ?: "No value produced in 1000ms")
}

/**
 * Take the latest value from the channel, or null if there is none. This method relies on shared
 * memory communications; Do not use in coroutines test code.
 */
fun <T> AwaitChannel<T>.takeValue(): T? {
    assertCallingContextIsNotSuspended()
    return asChannel().tryReceive().getOrNull()
}

/**
 * Take the last value from the channel, or null if there is none. This method relies on shared
 * memory communications; Do not use in coroutines test code.
 */
fun <T> AwaitChannel<T>.takeLastValue(): T? {
    assertCallingContextIsNotSuspended()

    var lastValue: T? = null
    while (!asChannel().isEmpty) {
        lastValue = takeValue()
    }

    return lastValue
}

/**
 * Take the current contents of the channel's buffer. This method relies on shared memory
 * communications; Do not use in coroutines test code.
 */
fun <T> AwaitChannel<T>.takeCurrentContents(): List<T> = buildList {
    assertCallingContextIsNotSuspended()

    val channel = asChannel()
    while (!channel.isEmpty) {
        try {
            add(channel.tryReceive().getOrThrow())
        } catch (e: Exception) {
            // ignore closed or failed channel
            return@buildList
        }
    }
}

/**
 * Repeatedly call [awaitValue], returning the first value that is an instance of [T].
 */
suspend inline fun <reified T> AwaitChannel<*>.awaitInstanceOf(): T {
    while (true) {
        val value = awaitValue()
        if (value is T)
            return value
    }
}

/**
 * Take a single value from the channel, throwing an assertion error if the channel is empty
 * or has more than a single value. This method relies on shared memory communications; Do not
 * use in coroutines test code.
 */
fun <T> AwaitChannel<T>.takeSingle(): T {
    assertCallingContextIsNotSuspended()

    val channel = asChannel()
    val value = channel.tryReceive()

    if (!value.isSuccess) {
        throw AssertionError("No values")
    } else if (!isEmpty) {
        throw AssertionError("More than one value returned")
    } else {
        return value.getOrThrow()
    }
}

/**
 * Clear the current contents of the channel. This method relies on shared memory communications;
 * Do not use in coroutines test code.
 */
fun <T> AwaitChannel<T>.clearCurrentContents() {
    takeCurrentContents()
}

operator fun <T> AwaitChannel<T>.plusAssign(value: T) { add(value) }