package app.vut.secnote.ui.main.categories

import app.vut.secnote.data.model.ui.NoteCategories
import com.thefuntasty.mvvm.event.Event

sealed class CategoriesEvent : Event<CategoriesViewState>()
object NavigateBackEvent : CategoriesEvent()
object CreateCategoryEvent : CategoriesEvent()
data class ChangeCategoriesEvent(val result: NoteCategories) : CategoriesEvent()
