package com.example.sphproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.sphproject.Models.DisplayDataModel
import com.example.sphproject.R
import java.util.ArrayList


class MobileDataAdapter(private val myDataset: ArrayList<DisplayDataModel>, private val context : Context) :
    RecyclerView.Adapter<MobileDataAdapter.MyViewHolder>() {

    inner class MyViewHolder(var layout: View) : RecyclerView.ViewHolder(layout) {
        // each data item is just a string in this case
        var txtYearData: TextView
        var imageView: ImageView


        init {
            txtYearData = layout.findViewById(R.id.txtYearData)
            imageView = layout.findViewById(R.id.imageview)
        }
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(
            parent.context
        )
        val v = inflater.inflate(R.layout.item_row, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtYearData.text =  myDataset[position].year + " \n " + myDataset[position].totalVol
        if(myDataset[position].hasDecreaseVol)
            holder.imageView.visibility = View.VISIBLE
        else
            holder.imageView.visibility = View.GONE

        holder.imageView.setOnClickListener {
            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}