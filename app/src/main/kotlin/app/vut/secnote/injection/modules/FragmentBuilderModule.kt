package app.vut.secnote.injection.modules

import app.vut.secnote.ui.main.categories.CategoriesFragment
import app.vut.secnote.ui.main.categories.CategoriesFragmentModule
import app.vut.secnote.ui.main.categories.create.CreateCategoryFragment
import app.vut.secnote.ui.main.categories.create.CreateCategoryFragmentModule
import app.vut.secnote.ui.main.encryption.EncryptionFragment
import app.vut.secnote.ui.main.encryption.EncryptionFragmentModule
import app.vut.secnote.ui.main.encryption.create.CreateKeyFragment
import app.vut.secnote.ui.main.encryption.create.CreateKeyFragmentModule
import app.vut.secnote.ui.main.error.ErrorDialogFragment
import app.vut.secnote.ui.main.error.ErrorDialogFragmentModule
import app.vut.secnote.ui.main.invite.InviteFragment
import app.vut.secnote.ui.main.invite.InviteFragmentModule
import app.vut.secnote.ui.main.login.LoginFragment
import app.vut.secnote.ui.main.login.LoginFragmentModule
import app.vut.secnote.ui.main.note.NoteFragment
import app.vut.secnote.ui.main.note.NoteFragmentModule
import app.vut.secnote.ui.main.notes.NotesFragment
import app.vut.secnote.ui.main.notes.NotesFragmentModule
import app.vut.secnote.ui.main.pin.PinFragment
import app.vut.secnote.ui.main.pin.PinFragmentModule
import app.vut.secnote.ui.main.profile.ProfileFragment
import app.vut.secnote.ui.main.profile.ProfileFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [CategoriesFragmentModule::class])
    abstract fun categoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector(modules = [CreateCategoryFragmentModule::class])
    abstract fun createCategoryFragment(): CreateCategoryFragment

    @ContributesAndroidInjector(modules = [EncryptionFragmentModule::class])
    abstract fun encryptionFragment(): EncryptionFragment

    @ContributesAndroidInjector(modules = [CreateKeyFragmentModule::class])
    abstract fun createKeyFragment(): CreateKeyFragment

    @ContributesAndroidInjector(modules = [InviteFragmentModule::class])
    abstract fun inviteFragment(): InviteFragment

    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [NoteFragmentModule::class])
    abstract fun noteFragment(): NoteFragment

    @ContributesAndroidInjector(modules = [NotesFragmentModule::class])
    abstract fun notesFragment(): NotesFragment

    @ContributesAndroidInjector(modules = [PinFragmentModule::class])
    abstract fun pinFragment(): PinFragment

    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector(modules = [ErrorDialogFragmentModule::class])
    abstract fun errorDialogFragment(): ErrorDialogFragment
}
