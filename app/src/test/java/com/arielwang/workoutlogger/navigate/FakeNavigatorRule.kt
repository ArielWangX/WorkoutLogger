package com.arielwang.workoutlogger.navigate

import com.arielwang.workoutlogger.testutils.AwaitChannelsRule

/**
 * Ensures that all navigated screens have been consumed on teardown.
 */
class FakeNavigatorRule(
    val navigator: FakeNavigator = FakeNavigator()
) : AwaitChannelsRule(navigator.channels)
