package app.vut.secnote.ui.main.notes

import dagger.Module
import dagger.Provides

@Module
class NotesFragmentModule {
    @Provides
    fun notesView(fr: NotesFragment) : NotesView = fr
}
