package app.vut.secnote.domain.categories

import app.vut.secnote.data.store.CategoryStore
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class SyncCategoryInteractor @Inject constructor(
    private val categoryStore: CategoryStore
) : BaseCoroutineInteractor<Unit>() {

    private lateinit var categories: List<String>

    fun init(categories: List<String>) = apply {
        this.categories = categories
    }

    override suspend fun build() = categoryStore.addAllCategories(categories)
}

