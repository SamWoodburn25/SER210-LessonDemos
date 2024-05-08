package edu.quinnipiac.ser210.lesson4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.ls04_recycleview.model.Affirmation

class ItemAdapter(private val context: Context, private var dataSet: List<Affirmation>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View, private val context: Context): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.item_title)
        val  imageView:ImageView = view.findViewById(R.id.item_image)
        private val curraffirmation: Affirmation? = null


        init {
            itemView.setOnClickListener{
                Toast.makeText(context, "a click event", Toast.LENGTH_LONG).show()
            }
        }

        fun bind(affirmation: Affirmation){
            textView.text = context.resources.getString(affirmation.stringResourceId)
            if(affirmation.image != null){
                imageView.setImageResource(affirmation.image)
            } else {
                imageView.setImageResource(R.drawable.img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context). inflate(R.layout.list_item, parent, false)
        return  ItemViewHolder(adapterLayout, context)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item  = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    fun addItemToDataset(): Int{
        dataSet = dataSet + Affirmation(R.string.affirmation1, R.drawable.img)
        notifyItemInserted(dataSet.size)
        return dataSet.size
    }

}