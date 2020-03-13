package app.vut.secnote.ui.main.categories.create

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class CreateCategoryViewModel @Inject constructor(
    override val viewState: CreateCategoryViewState
) : BaseCrViewModel<CreateCategoryViewState>()

