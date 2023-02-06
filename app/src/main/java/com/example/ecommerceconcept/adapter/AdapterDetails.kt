package com.example.ecommerceconcept.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ImageItemBinding
import com.example.ecommerceconcept.databinding.ImagePhoneBinding
import com.example.ecommerceconcept.model.category.Image
import com.example.ecommerceconcept.model.category.ProductDeteils

class AdapterDetails(val context: Context):RecyclerView.Adapter<AdapterDetails.DetailsViewHolder>() {
    private var listDetail:List<String> = listOf()
    fun getDeteilList(productDeteils: List<String>){
        listDetail = productDeteils
        notifyDataSetChanged()
    }

    class DetailsViewHolder(var binding: ImagePhoneBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(listDetail: String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view = DetailsViewHolder(ImagePhoneBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        return view
        }

    override fun getItemCount(): Int {
        return listDetail.size
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
            val list = listDetail[position]
             holder.bind(list)

        Glide.with(context)
            .load(list.toString())
            .placeholder(R.drawable.smart)
            .into(holder.binding.imageProduct)

    }


}