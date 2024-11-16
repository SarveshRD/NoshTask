package navigation.cook

import com.arkivanov.decompose.ComponentContext

class DefaultCookComponent(
    componentContext: ComponentContext,
    private val onShowBackClicked: () -> Unit
) : CookComponent,
    ComponentContext by componentContext {
    override fun onBackClicked() {
        onShowBackClicked()
    }
}
