package app.vut.secnote.ui.main.note

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class NoteViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<NoteViewModel>
) : BaseViewModelFactory<NoteViewModel>() {
    override val viewModelClass: KClass<NoteViewModel> = NoteViewModel::class
}
