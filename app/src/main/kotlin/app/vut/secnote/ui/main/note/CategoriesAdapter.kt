package app.vut.secnote.ui.main.note

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.vut.secnote.databinding.ListItemCategoryAddBinding
import app.vut.secnote.databinding.ListItemCategorySelectionBinding
import app.vut.secnote.tools.extensions.layoutInflater
import javax.inject.Inject

class CategoriesAdapter @Inject constructor(
    private val view: NoteView
) :
    ListAdapter<String, CategoriesAdapter.CategoryViewHolder>(
        DiffUtilCallback
    ) {

    companion object {
        const val ADD_CATEGORY = 1
        const val CATEGORY = 2
    }

    override fun getItemCount(): Int = super.getItemCount() + ADD_CATEGORY

    override fun getItemViewType(position: Int): Int = when (position) {
        itemCount - 1 -> ADD_CATEGORY
        else -> CATEGORY
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        when (holder) {
            is CategoryAddViewHolder -> holder.bind()
            is CategorySelectionViewHolder -> holder.bind(getItem(position))
            else -> error("Invalid viewholder")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder = when (viewType) {
        ADD_CATEGORY -> ListItemCategoryAddBinding.inflate(
            parent.layoutInflater(),
            parent,
            false
        ).let {
            CategoryAddViewHolder(it, view)
        }
        CATEGORY -> ListItemCategorySelectionBinding.inflate(
            parent.layoutInflater(),
            parent,
            false
        ).let {
            CategorySelectionViewHolder(it, view)
        }
        else -> error("Invalid view type")
    }

    abstract class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class CategoryAddViewHolder(
        private val binding: ListItemCategoryAddBinding,
        private val view: NoteView
    ) : CategoryViewHolder(binding.root) {

        fun bind() {
            binding.view = view
            binding.executePendingBindings()
        }
    }

    class CategorySelectionViewHolder(
        private val binding: ListItemCategorySelectionBinding,
        private val view: NoteView
    ) : CategoryViewHolder(binding.root) {

        fun bind(item: String) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<String>() {
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean =
            oldItem == oldItem
    }
}
