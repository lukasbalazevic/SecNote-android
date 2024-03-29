package app.vut.secnote.ui.main.note

import dagger.Module
import dagger.Provides

@Module
class NoteFragmentModule {
    @Provides
    fun view(fr: NoteFragment): NoteView = fr

    @Provides
    fun arguments(fr: NoteFragment) = NoteFragmentArgs.fromBundle(fr.requireArguments())
}
