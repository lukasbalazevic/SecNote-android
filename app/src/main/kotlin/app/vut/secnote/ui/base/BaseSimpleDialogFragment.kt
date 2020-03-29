package app.vut.secnote.ui.base

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import app.vut.secnote.tools.Constants
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BaseSimpleDialogFragment : DialogFragment() {

    private val listener: DialogListener?
        get() = requireParentFragment().childFragmentManager.fragments.first() as? DialogListener

    var dialogTag: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return MaterialAlertDialogBuilder(requireContext()).apply {

            requireArguments().let { bundle ->
                bundle.getString(Constants.Bundle.DIALOG_TAG)?.let {
                    dialogTag = it
                }
                bundle.getString(Constants.Bundle.DIALOG_TITLE)?.let {
                    setTitle(it)
                }
                bundle.getString(Constants.Bundle.DIALOG_BODY)?.let {
                    setMessage(it)
                }
                bundle.getString(Constants.Bundle.DIALOG_POSITIVE)?.let {
                    setPositiveButton(it) { _, _ -> listener?.onPositive(dialogTag) }
                }
                bundle.getString(Constants.Bundle.DIALOG_NEGATIVE)?.let {
                    setNegativeButton(it) { _, _ -> listener?.onNegative(dialogTag) }
                }
                bundle.getString(Constants.Bundle.DIALOG_NEUTRAL)?.let {
                    setNeutralButton(it) { _, _ -> listener?.onNeutral(dialogTag) }
                }
                bundle.getInt(Constants.Bundle.DIALOG_SINGLE_CHOICE_ARRAY_RES).takeIf { it != 0 }?.also { arrayResId ->
                    setSingleChoiceItems(
                        arrayResId,
                        bundle.getInt(Constants.Bundle.DIALOG_SINGLE_CHOICE_PRESET_POSITION)
                    ) { _, index -> listener?.onSingleChoiceChanged(dialogTag, index) }
                }
            }
        }.show()
    }

    override fun onStop() {
        listener?.onCancel(dialogTag)
        super.onStop()
    }

    interface DialogListener {
        fun onPositive(tag: String?) = Unit

        fun onNegative(tag: String?) = Unit

        fun onNeutral(tag: String?) = Unit

        fun onCancel(tag: String?) = Unit

        fun onSingleChoiceChanged(tag: String?, index: Int) = Unit
    }
}
