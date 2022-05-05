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
    private val onClick: (MainActivity.Item) -> Unit) :
    ListAdapter<MainActivity.Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback) {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemListBinding = ItemListBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = getItem(position)
        holder.binding.textView.text = item.number.toString()
        holder.binding.cardView.setCardBackgroundColor(Color.parseColor(item.color))

        holder.itemView.setOnClickListener {
          item.let(onClick)
        }
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