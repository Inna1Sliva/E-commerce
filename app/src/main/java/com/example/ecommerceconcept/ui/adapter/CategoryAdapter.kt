package com.example.ecommerceconcept.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.domain.model.category.Category

class CategoryAdapter( val context:Context, val category: List<Category>):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var select:Boolean = true

    class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var image : ImageView = view.findViewById(R.id.imageCategory)
        var const : ConstraintLayout = view.findViewById(R.id.constLayout)
        var name: TextView = view.findViewById(R.id.nameCategory)
      fun bind(list: Category){
            image.setImageResource(list.image)
            const.setBackgroundResource(list.color)
            name.text = list.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_category,parent,false)
            return CategoryViewHolder(view)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val list = category[position]
        holder.bind(list)
        holder.image.setImageResource(list.image)
        holder.const.setBackgroundResource(list.color)
        holder.name.text = list.name



    if (select) {
        if (position == 0) {
            holder.const.setBackgroundResource(R.drawable.ok_pressed)
            holder.image.setImageResource(R.drawable.phone_white)
         holder.name.setTextColor(Color.parseColor("#FF6E4E"))
            select = false
        }
    }


}
            //}


            override fun getItemCount(): Int {
                return category.size
            }
        }

