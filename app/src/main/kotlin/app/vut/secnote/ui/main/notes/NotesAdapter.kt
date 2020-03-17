package app.vut.secnote.ui.main.notes

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.vut.secnote.databinding.ListItemNoteBinding
import app.vut.secnote.noteservice.Note
import app.vut.secnote.tools.extensions.layoutInflater
import javax.inject.Inject

class NotesAdapter @Inject constructor(
    private val view: NotesView
) :
    ListAdapter<Note, NotesAdapter.NoteViewHolder>(
        DiffUtilCallback
    ) {

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        return ListItemNoteBinding.inflate(
            parent.layoutInflater(),
            parent,
            false
        ).let {
            NoteViewHolder(it, view)
        }
    }

    class NoteViewHolder(
        private val binding: ListItemNoteBinding,
        private val view: NotesView
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Note) {
            binding.item = item
            binding.view = view
            binding.executePendingBindings()
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Note>() {
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean =
            oldItem.id == oldItem.id
    }
}