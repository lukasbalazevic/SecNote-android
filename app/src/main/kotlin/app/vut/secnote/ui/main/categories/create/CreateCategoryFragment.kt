package app.vut.secnote.ui.main.categories.create

import android.os.Bundle
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentCreateCategoryBinding
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class CreateCategoryFragment : BaseBindingFragment<CreateCategoryViewModel, CreateCategoryViewState, FragmentCreateCategoryBinding>(), CreateCategoryView {


    @Inject override lateinit var viewModelFactory: CreateCategoryViewModelFactory

    override val layoutResId = R.layout.fragment_create_category

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent(NavigateBack::class) {
            navigateBack()
        }
    }
}
