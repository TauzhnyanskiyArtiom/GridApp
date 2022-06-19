package ua.opu.gridapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
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
        private val value = view.findViewById<TextView>(R.id.valueIn)
        private val circleElement = view.findViewById<MaterialCardView>(R.id.circle_element)

        fun bind(element: MainActivity.Element) {
            value.text = element.number.toString()
            circleElement.setCardBackgroundColor(Color.parseColor(element.color))

            itemView.setOnClickListener {
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