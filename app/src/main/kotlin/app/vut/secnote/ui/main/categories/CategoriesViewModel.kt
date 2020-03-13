package app.vut.secnote.ui.main.categories

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    override val viewState: CategoriesViewState
) : BaseCrViewModel<CategoriesViewState>()

