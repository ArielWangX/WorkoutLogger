package com.arielwang.workoutlogger.testutils

import org.junit.rules.ExternalResource

/**
 * A rule that ensures that all events from [AwaitChannels] have been consumed by the end of a test.
 */
open class AwaitChannelsRule(private val awaitChannels: AwaitChannels) :
    ExternalResource() {
    override fun after() {
        awaitChannels.assertEmpty(ruleName = this::class.simpleName!!)
    }
}