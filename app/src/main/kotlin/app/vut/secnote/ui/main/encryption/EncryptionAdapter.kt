package app.vut.secnote.ui.main.encryption

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.vut.secnote.data.model.ui.KeySelection
import app.vut.secnote.databinding.ListItemKeyBinding
import app.vut.secnote.tools.extensions.layoutInflater
import javax.inject.Inject

class EncryptionAdapter @Inject constructor(
    private val view: EncryptionView
) : ListAdapter<KeySelection, EncryptionAdapter.EncryptionViewHolder>(DiffUtilCallback) {

    override fun onBindViewHolder(holder: EncryptionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EncryptionViewHolder = ListItemKeyBinding.inflate(
        parent.layoutInflater(),
        parent,
        false
    ).let {
        EncryptionViewHolder(it, view)
    }

    class EncryptionViewHolder(
        private val binding: ListItemKeyBinding,
        private val view: EncryptionView
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(key: KeySelection) {
            binding.view = view
            binding.alias = key.alias
            binding.root.isSelected = key.selected
            binding.executePendingBindings()
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<KeySelection>() {
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: KeySelection,
            newItem: KeySelection
        ): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(
            oldItem: KeySelection,
            newItem: KeySelection
        ): Boolean =
            oldItem.alias == oldItem.alias
    }
}
