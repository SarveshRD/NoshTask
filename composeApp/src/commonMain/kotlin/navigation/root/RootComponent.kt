package navigation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import navigation.cook.CookComponent
import navigation.device.DeviceComponent
import navigation.favourite.FavoriteComponent
import navigation.manual.ManualComponent
import navigation.preference.PreferenceComponent
import navigation.setting.SettingComponent

interface RootComponent {

    val routerState: Value<ChildStack<*, RootChild>>

    fun navigateToCook()
    fun navigateToFavorite()
    fun navigateToManual()
    fun navigateToDevice()
    fun navigateToPreferences()
    fun navigateToSettings()

    sealed class RootChild {
        data class Cook(val component: CookComponent) : RootChild()
        data class Favorite(val component: FavoriteComponent) : RootChild()
        data class Manual(val component: ManualComponent) : RootChild()
        data class Device(val component: DeviceComponent) : RootChild()
        data class Preferences(val component: PreferenceComponent) : RootChild()
        data class Settings(val component: SettingComponent) : RootChild()
    }
}