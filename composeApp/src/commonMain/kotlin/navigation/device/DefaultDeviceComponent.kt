package navigation.device

import com.arkivanov.decompose.ComponentContext

class DefaultDeviceComponent(
    componentContext: ComponentContext,
    private val onShowBackClicked: () -> Unit,) : DeviceComponent,
    ComponentContext by componentContext {
    override fun onBackClicked() {
        onShowBackClicked()
    }
}
