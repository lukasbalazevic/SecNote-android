package app.vut.secnote.ui.main.categories

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentCategoriesBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class CategoriesFragment : BaseBindingFragment<CategoriesViewModel, CategoriesViewState, FragmentCategoriesBinding>(), CategoriesView {


    @Inject override lateinit var viewModelFactory: CategoriesViewModelFactory

    override val layoutResId = R.layout.fragment_categories
}
