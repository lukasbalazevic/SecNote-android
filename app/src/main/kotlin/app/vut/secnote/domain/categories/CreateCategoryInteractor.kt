package app.vut.secnote.domain.categories

import app.vut.secnote.data.store.CategoryStore
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class CreateCategoryInteractor @Inject constructor(
    private val categoryStore: CategoryStore
) : BaseCoroutineInteractor<Unit>() {

    private lateinit var name: String

    fun init(name: String) = apply {
        this.name = name
    }

    override suspend fun build() = categoryStore.addCategory(name)
}
