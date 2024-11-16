package navigation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import navigation.cook.CookComponent
import navigation.cook.DefaultCookComponent
import navigation.device.DefaultDeviceComponent
import navigation.device.DeviceComponent
import navigation.root.DefaultRootComponent.RootConfig.*
import navigation.root.RootComponent.RootChild
import navigation.favourite.DefaultFavoriteComponent
import navigation.favourite.FavoriteComponent
import navigation.manual.DefaultManualComponent
import navigation.manual.ManualComponent
import navigation.preference.DefaultPreferenceComponent
import navigation.preference.PreferenceComponent
import navigation.setting.DefaultSettingComponent
import navigation.setting.SettingComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent,
    ComponentContext by componentContext {

    private val rootNavigation = StackNavigation<RootConfig>()

    override val routerState: Value<ChildStack<*, RootChild>> =
        childStack(
            source = rootNavigation,
            serializer = RootConfig.serializer(),
            initialStack = { listOf(RootConfig.Cook) },
            handleBackButton = true,
            childFactory = ::child,
        )

    override fun navigateToCook() {
        rootNavigation.replaceCurrent(Cook)
    }

    override fun navigateToFavorite() {
        rootNavigation.replaceCurrent(Favorite)
    }

    override fun navigateToManual() {
        rootNavigation.replaceCurrent(Manual)
    }

    override fun navigateToDevice() {
        rootNavigation.replaceCurrent(Device)
    }

    override fun navigateToPreferences() {
        rootNavigation.replaceCurrent(Preferences)
    }

    override fun navigateToSettings() {
        rootNavigation.replaceCurrent(Setting)
    }

    private fun child(config: RootConfig, componentContext: ComponentContext): RootChild =
        when (config) {
            is Cook -> RootChild.Cook(
                cookComponent(
                    componentContext
                )
            )

            Device -> RootChild.Device(
                deviceComponent(
                    componentContext
                )
            )

            Favorite -> RootChild.Favorite(
                favoriteComponent(
                    componentContext
                )
            )
            Manual -> RootChild.Manual(
                manualComponent(
                    componentContext
                )
            )
            Preferences -> RootChild.Preferences(
                preferenceComponent(
                    componentContext
                )
            )
            Setting -> RootChild.Settings(
                settingComponent(
                    componentContext
                )
            )
        }


    @Serializable
    private sealed interface RootConfig {
        @Serializable
        data object Cook : RootConfig

        @Serializable
        data object Favorite : RootConfig

        @Serializable
        data object Manual : RootConfig
        @Serializable
        data object Device : RootConfig

        @Serializable
        data object Preferences : RootConfig
        @Serializable
        data object Setting : RootConfig
    }

    private fun cookComponent(componentContext: ComponentContext): CookComponent =
        DefaultCookComponent(componentContext = componentContext,
            onShowBackClicked = { rootNavigation.pop() })

    private fun favoriteComponent(componentContext: ComponentContext): FavoriteComponent =
        DefaultFavoriteComponent(componentContext = componentContext,
            onShowBackClicked = { rootNavigation.pop() })

    private fun manualComponent(componentContext: ComponentContext): ManualComponent =
        DefaultManualComponent(componentContext = componentContext,
            onShowBackClicked = { rootNavigation.pop() })

    private fun deviceComponent(componentContext: ComponentContext): DeviceComponent =
        DefaultDeviceComponent(componentContext = componentContext,
            onShowBackClicked = { rootNavigation.pop() })

    private fun preferenceComponent(componentContext: ComponentContext): PreferenceComponent =
        DefaultPreferenceComponent(componentContext = componentContext,
            onShowBackClicked = { rootNavigation.pop() })

    private fun settingComponent(componentContext: ComponentContext): SettingComponent =
        DefaultSettingComponent(componentContext = componentContext,
            onShowBackClicked = { rootNavigation.pop() })


}