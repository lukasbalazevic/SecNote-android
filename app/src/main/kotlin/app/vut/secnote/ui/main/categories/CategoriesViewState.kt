package app.vut.secnote.ui.main.categories

import app.vut.secnote.data.model.ui.CategorySelection
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import com.thefuntasty.mvvm.livedata.combineLiveData
import javax.inject.Inject

class CategoriesViewState @Inject constructor(
    arguments: CategoriesFragmentArgs
) : ViewState {
    val selectedCategories = DefaultValueLiveData(arguments.noteCategories?.categories?.toHashSet() ?: HashSet())
    val categories = DefaultValueLiveData<List<String>>(emptyList())

    val categoriesList = combineLiveData(categories, selectedCategories) { list, selected ->
        list.map {
            CategorySelection(it, selected.contains(it))
        }
    }
}
