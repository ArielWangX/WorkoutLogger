package com.arielwang.workoutlogger.navigate

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.arielwang.workoutlogger.testutils.AwaitChannels
import com.arielwang.workoutlogger.testutils.awaitValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class FakeNavigator : Navigator {
    val channels = AwaitChannels()

    private val navigatedScreens = channels.create<FakeNavigationBehaviour>()

    override val destinations: Flow<NavigatorEvent>
        get() = emptyFlow()

    override fun navigateUp(): Boolean {
        navigatedScreens.add(FakeNavigationBehaviour.NavigateUp)
        return true
    }

    override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit): Boolean {
        navigatedScreens.add(FakeNavigationBehaviour.Navigate(route))
        return true
    }

    override fun popToRoot(): Boolean {
        navigatedScreens.add(FakeNavigationBehaviour.PopToRoute)
        return true
    }

    override fun navigateToRoot(route: String): Boolean {
        navigatedScreens.add(FakeNavigationBehaviour.NavigateToRoot(route))
        return true
    }

    override fun popTo(route: String, inclusive: Boolean): Boolean {
        navigatedScreens.add(FakeNavigationBehaviour.PopTo(route))
        return true
    }

    override fun openDeepLink(uri: Uri): Boolean {
        navigatedScreens.add(FakeNavigationBehaviour.OpenDeepLink(uri))
        return true
    }

    override fun attachToNavController(navController: NavController) {
    }

    override fun getCurrentScreenId(): String? = null

    override fun getPreviousScreenId(): String? = null

    override fun getCurrentBackStackSavedState(): SavedStateHandle? = null

    override fun getPreviousBackStackSavedState(): SavedStateHandle? = null

    suspend fun awaitNextScreen() = navigatedScreens.awaitValue()
}

sealed class FakeNavigationBehaviour {
    object NavigateUp : FakeNavigationBehaviour()
    data class Navigate(val route: String) : FakeNavigationBehaviour()
    data class PopTo(val route: String) : FakeNavigationBehaviour()
    data class NavigateToRoot(val route: String) : FakeNavigationBehaviour()
    object PopToRoute : FakeNavigationBehaviour()
    data class OpenDeepLink(val uri: Uri) : FakeNavigationBehaviour()
}