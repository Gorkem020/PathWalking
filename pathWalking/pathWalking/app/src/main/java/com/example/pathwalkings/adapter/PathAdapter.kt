package com.example.pathwalkings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pathwalkings.data.Path
import com.example.pathwalkings.R
import com.google.android.material.imageview.ShapeableImageView

class PathAdapter(private val pathList : List<Path>) : RecyclerView.Adapter<PathAdapter.PathViewHolder>() {


    private lateinit var mListener : onItemClickListener//interfacesin değişkeni oluşturuldu


    interface onItemClickListener{//adapter tıklanma için
        fun onItemClick(position : Int)
    }

    fun setOnClickListener(listener : onItemClickListener){

        mListener = listener
    }

    inner class PathViewHolder(itemView : View, listener : onItemClickListener) :RecyclerView.ViewHolder(itemView){
        var ivPath    : ShapeableImageView = itemView.findViewById(R.id.ivPath)
        var tvHeading : TextView           = itemView.findViewById(R.id.tvHeading)

        init {
            //listener için oluştuurlmuş

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.path_list_item_cview,parent,false)
        return PathViewHolder(view,mListener)

    }

    override fun getItemCount(): Int {
       return pathList.size
    }

    override fun onBindViewHolder(holder: PathViewHolder, position: Int) {
       val pathPosition = pathList[position]

        holder.ivPath.setImageResource(pathPosition.imageVPath)
        holder.tvHeading.text  = pathPosition.heading





    }
}