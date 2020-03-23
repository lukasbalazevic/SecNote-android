package app.vut.secnote.ui.main.categories.create

import app.vut.secnote.domain.categories.CreateCategoryInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class CreateCategoryViewModel @Inject constructor(
    override val viewState: CreateCategoryViewState,
    private val createCategoryInteractor: CreateCategoryInteractor
) : BaseCrViewModel<CreateCategoryViewState>() {
    fun navigateBack() {
        sendEvent(NavigateBack)
    }

    fun createCategory() {
        val name = viewState.name.value
        if (name.isNotBlank()) {
            createCategoryInteractor.init(name).execute(
                onSuccess = {
                    sendEvent(NavigateBack)
                }
            )
        }
    }
}

