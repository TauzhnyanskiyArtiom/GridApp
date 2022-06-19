package ua.opu.gridapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.opu.gridapp.databinding.ItemListBinding

class ElementAdapter(
    private val context: Context,
    private val onClick: (MainActivity.Element) -> Unit
) :
    ListAdapter<MainActivity.Element, ElementAdapter.ItemViewHolder>(ItemDiffCallback) {

    inner class ItemViewHolder(
        view: View,
        private val onClick: (MainActivity.Element) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding: ItemListBinding = ItemListBinding.bind(itemView)

        fun bind(element: MainActivity.Element) {
            this.binding.textView.text = element.number.toString()
            this.binding.cardView.setCardBackgroundColor(Color.parseColor(element.color))

            this.itemView.setOnClickListener {
                element.let(onClick)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(v, onClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object ItemDiffCallback : DiffUtil.ItemCallback<MainActivity.Element>() {
        override fun areItemsTheSame(
            oldElement: MainActivity.Element,
            newElement: MainActivity.Element
        ): Boolean = oldElement == newElement

        override fun areContentsTheSame(
            oldElement: MainActivity.Element,
            newElement: MainActivity.Element
        ): Boolean = oldElement == newElement

    }
}