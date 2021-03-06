package com.example.advancedclipboardandroid

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ItemAdapter(
    private val context: Context,
    private val dataset: List<ClipboardItem>
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val fileIcon: ImageView = view.findViewById(R.id.file_icon)
        val ListItem: ListItem = view as ListItem
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.visibility = if (TextUtils.isEmpty(item.name)) View.GONE else View.VISIBLE

        if(item.contentTypeId == ContentTypes.PlainText)
        {
            holder.imageView.visibility = View.GONE
            holder.textView.visibility = View.VISIBLE
            holder.textView.text = item.name
            holder.fileIcon.visibility = View.GONE
        }
        else if (item.contentTypeId == ContentTypes.Image)
        {
            holder.imageView.visibility = View.VISIBLE
            holder.textView.visibility = View.GONE
            Glide.with(holder.view)
                 .load(item.fileUrl.toString())
                 .into(holder.imageView);
            holder.fileIcon.visibility = View.GONE
        }
        else if (item.contentTypeId == ContentTypes.File)
        {
            holder.imageView.visibility = View.GONE
            holder.textView.visibility = View.VISIBLE
            holder.textView.text = item.fileName
            holder.fileIcon.visibility = View.VISIBLE
        }

        holder.ListItem.item = item
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size
}