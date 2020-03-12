package app.vut.secnote.ui.main

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentMainBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class MainFragment : BaseBindingFragment<MainViewModel, MainViewState, FragmentMainBinding>(),
    MainView {

    @Inject
    override lateinit var viewModelFactory: MainViewModelFactory

    override val layoutResId = R.layout.fragment_main
}
