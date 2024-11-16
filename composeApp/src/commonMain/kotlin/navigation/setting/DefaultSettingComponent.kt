package navigation.setting

import com.arkivanov.decompose.ComponentContext

class DefaultSettingComponent(
    componentContext: ComponentContext,
    private val onShowBackClicked: () -> Unit,) : SettingComponent,
    ComponentContext by componentContext {
    override fun onBackClicked() {
        onShowBackClicked()
    }
}
