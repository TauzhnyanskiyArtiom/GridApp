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

class ItemAdapter(
    private val context: Context,
    private val onClick: (MainActivity.Item) -> Unit
) :
    ListAdapter<MainActivity.Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback) {

    inner class ItemViewHolder(
        view: View,
        private val onClick: (MainActivity.Item) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding: ItemListBinding = ItemListBinding.bind(itemView)

        fun bind(item: MainActivity.Item) {
            this.binding.textView.text = item.number.toString()
            this.binding.cardView.setCardBackgroundColor(Color.parseColor(item.color))

            this.itemView.setOnClickListener {
                item.let(onClick)
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

    object ItemDiffCallback : DiffUtil.ItemCallback<MainActivity.Item>() {
        override fun areItemsTheSame(
            oldItem: MainActivity.Item,
            newItem: MainActivity.Item
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MainActivity.Item,
            newItem: MainActivity.Item
        ): Boolean = oldItem == newItem

    }
}