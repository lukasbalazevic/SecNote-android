package app.vut.secnote.ui.main.notes

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class NotesViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<NotesViewModel>
) : BaseViewModelFactory<NotesViewModel>() {
    override val viewModelClass: KClass<NotesViewModel> = NotesViewModel::class
}
