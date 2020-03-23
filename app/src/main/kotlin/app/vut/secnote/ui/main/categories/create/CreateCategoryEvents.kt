package app.vut.secnote.ui.main.categories.create

import com.thefuntasty.mvvm.event.Event

sealed class CreateCategoryEvent : Event<CreateCategoryViewState>()
object NavigateBack : CreateCategoryEvent()
