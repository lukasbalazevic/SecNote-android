package app.vut.secnote.ui.main.categories.create

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentCreateCategoryBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class CreateCategoryFragment : BaseBindingFragment<CreateCategoryViewModel, CreateCategoryViewState, FragmentCreateCategoryBinding>(), CreateCategoryView {


    @Inject override lateinit var viewModelFactory: CreateCategoryViewModelFactory

    override val layoutResId = R.layout.fragment_create_category
}
