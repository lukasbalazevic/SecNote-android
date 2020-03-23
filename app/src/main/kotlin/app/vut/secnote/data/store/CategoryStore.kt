package app.vut.secnote.data.store

import app.vut.secnote.data.database.ApplicationDatabase
import app.vut.secnote.data.model.room.Category
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryStore @Inject constructor(private val db: ApplicationDatabase) {
    fun addCategory(name: String) = db.categoryDao().insert(Category(name))
    fun addAllCategories(categories: List<String>) = db.categoryDao().insertAll(categories.map { Category(it) })
    fun getCategories() = db.categoryDao().geCategories().map { it.map { it.name } }
}
