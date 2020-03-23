package app.vut.secnote.ui.main.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.data.model.ui.CategorySelection
import app.vut.secnote.data.model.ui.NoteCategories
import app.vut.secnote.databinding.FragmentCategoriesBinding
import app.vut.secnote.databinding.ListItemCategoryFilterBinding
import app.vut.secnote.tools.Constants
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.tools.extensions.setResult
import app.vut.secnote.ui.base.BaseBindingFragment
import com.thefuntasty.mvvm.livedata.observeNonNull
import javax.inject.Inject


class CategoriesFragment : BaseBindingFragment<CategoriesViewModel, CategoriesViewState, FragmentCategoriesBinding>(), CategoriesView {

    @Inject override lateinit var viewModelFactory: CategoriesViewModelFactory

    override val layoutResId = R.layout.fragment_categories

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        observeEvent(NavigateBackEvent::class) {
            navigateBack()
        }

        observeEvent(CreateCategoryEvent::class) {
            navigateTo(
                CategoriesFragmentDirections.navigateToCreateCategoryFragment()
            )
        }

        observeEvent(ChangeCategoriesEvent::class) {
            setResult(it.result)
        }

        viewModel.viewState.categoriesList.observeNonNull(this) {
            binding.categoriesGroup.removeAllViews()
            it.forEach {
                binding.categoriesGroup.addView(createSelectionChip(it))
            }
        }
    }

    private fun setResult(result: NoteCategories) {
        setResult(Constants.Note.CATEGORIES_CHANGE, result)
    }

    private fun createSelectionChip(category: CategorySelection) =
        ListItemCategoryFilterBinding.inflate(
            LayoutInflater.from(requireContext()),
            binding.categoriesGroup,
            false
        ).apply {
            item = category.name
            itemChip.isChecked = category.selected
            itemChip.setOnCheckedChangeListener { _, _ ->
                viewModel.invertState(category)
            }
            executePendingBindings()
        }.root
}
