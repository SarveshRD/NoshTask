package navigation.favourite

import com.arkivanov.decompose.ComponentContext

class DefaultFavoriteComponent(
    componentContext: ComponentContext,
    private val onShowBackClicked: () -> Unit,) : FavoriteComponent,
    ComponentContext by componentContext {
    override fun onBackClicked() {
        onShowBackClicked()
    }
}
