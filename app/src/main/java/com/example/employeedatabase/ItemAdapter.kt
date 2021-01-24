package com.example.employeedatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

/**
 * Creates the view holder for view items, connects the data source of the RecyclerView and handles the view logic by creating a RecyclerView Adapter.
 */
class ItemAdapter (val context: Context, val items: ArrayList<EmployeeModel>) :
    /**
    Inflates the item views which is designed in the XML layout file,
    creates a new ViewHolder and initializes some private fields to be used by RecyclerView.
     */
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
    }

    /**
    Binds each item in the ArrayList to a view.
    It's called when RecyclerView needs a new ViewHolder of the given type to represent an item.
    This new ViewHolder should be constructed with a new View that can represent the items of the given type.
    You can either create a new View manually or inflate it from an SML layout file.
     */
    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item = items.get(position)

        holder.tvName.text = item.name
        holder.tvEmail.text = item.email

        //Updating the background color according to the odd/even positions in the list
        if (position % 2 == 0) {
            holder.itemRowLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLightGray))
        }
        else {
            holder.itemRowLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }

        //Occurs when the edit button is clicked
        holder.ivEdit.setOnClickListener {
            if (context is MainActivity) {
                context.updateRecordDialog(item)
            }
        }

        //Occurs with the delete button is clicked
        holder.ivDelete.setOnClickListener {
            if (context is MainActivity) {
                context.deleteRecordAlertDialog(item)
            }
        }
    }

    /**
    Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
    The ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemRowLayout = view.itemRowLayout
        val tvName = view.tvName
        val tvEmail = view.tvEmail
        val ivEdit = view.ivEdit
        val ivDelete = view.ivDelete
    }

}