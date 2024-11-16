package navigation.manual

import com.arkivanov.decompose.ComponentContext

class DefaultManualComponent(
    componentContext: ComponentContext,
    private val onShowBackClicked: () -> Unit,) : ManualComponent,
    ComponentContext by componentContext {
    override fun onBackClicked() {
        onShowBackClicked()
    }
}
