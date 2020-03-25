package app.vut.secnote.domain.categories

import app.vut.secnote.data.store.CategoryStore
import com.thefuntasty.mvvm.crinteractors.BaseFlowInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesInteractor @Inject constructor(
    private val categoryStore: CategoryStore
) : BaseFlowInteractor<List<String>>() {
    override fun build(): Flow<List<String>> = categoryStore.getCategories()
}
