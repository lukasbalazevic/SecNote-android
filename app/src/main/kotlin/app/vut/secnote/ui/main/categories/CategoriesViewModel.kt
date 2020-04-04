package app.vut.secnote.ui.main.categories

import app.vut.secnote.data.model.ui.CategorySelection
import app.vut.secnote.data.model.ui.NoteCategories
import app.vut.secnote.domain.categories.GetCategoriesInteractor
import app.vut.secnote.domain.categories.SyncCategoryInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    override val viewState: CategoriesViewState,
    private val getCategoriesInteractor: GetCategoriesInteractor,
    private val syncCategoryInteractor: SyncCategoryInteractor
) : BaseCrViewModel<CategoriesViewState>() {

    override fun onStart() {
        if (viewState.selectedCategories.value.isNotEmpty()) {
            syncCategoryInteractor.init(viewState.selectedCategories.value.toList()).execute {
                getCategories()
            }
        } else {
            getCategories()
        }
    }

    private fun getCategories() {
        getCategoriesInteractor.execute(
            onNext = {
                viewState.categories.value = it
                viewState.loading.value = false
            }
        )
    }

    fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }

    fun createCategory() {
        sendEvent(CreateCategoryEvent)
    }

    fun invertState(category: CategorySelection) {
        if (category.selected) {
            viewState.selectedCategories.value = viewState.selectedCategories.value.apply { remove(category.name) }
        } else {
            viewState.selectedCategories.value = viewState.selectedCategories.value.apply { add(category.name) }
        }
        sendEvent(ChangeCategoriesEvent(
            NoteCategories(
                viewState.selectedCategories.value.toList()
            )
        ))
    }
}

