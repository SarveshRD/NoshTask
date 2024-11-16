package navigation.preference

import com.arkivanov.decompose.ComponentContext

class DefaultPreferenceComponent(
    componentContext: ComponentContext,
    private val onShowBackClicked: () -> Unit,) : PreferenceComponent,
    ComponentContext by componentContext {
    override fun onBackClicked() {
        onShowBackClicked()
    }
}
