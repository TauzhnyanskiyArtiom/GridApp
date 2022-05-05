package ua.opu.gridapp

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ItemDialog : DialogFragment() {

    private var selected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selected = arguments?.getInt("selectedNumber") as Int
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle("Number")
            .setMessage("You choose number: $selected")
            .setPositiveButton("Ok", null)
            .create()
    }

    companion object {
        fun newInstance(selectedNumber:Int): ItemDialog {
            val args = Bundle()
            args.putInt("selectedNumber", selectedNumber)
            val fragment = ItemDialog()
            fragment.arguments = args
            return fragment
        }
    }
}