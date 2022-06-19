package ua.opu.gridapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ElementDialog : DialogFragment() {

    lateinit var listener: ItemDialogListener
    private var selected: Int = 0

    interface ItemDialogListener {
        fun onDialogResult()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as ItemDialogListener
        } catch (e: ClassCastException) {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selected = arguments?.getInt(SELECTED_NUMBER_MESSAGE) as Int
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle("Number")
            .setMessage("You choose number: $selected")
            .setPositiveButton("Ok") { _, _ -> listener.onDialogResult() }
            .create()
    }

    companion object {
        const val SELECTED_NUMBER_MESSAGE = "selectedNumber"

        fun newInstance(selectedNumber:Int): ElementDialog {
            val args = Bundle()
            args.putInt(SELECTED_NUMBER_MESSAGE, selectedNumber)
            val fragment = ElementDialog()
            fragment.arguments = args
            return fragment
        }
    }
}