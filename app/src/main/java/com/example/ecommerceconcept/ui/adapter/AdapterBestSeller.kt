package com.example.ecommerceconcept.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ImageItemBinding
import com.example.ecommerceconcept.domain.model.category.BestSeller

class AdapterBestSeller( val context: Context):RecyclerView.Adapter<AdapterBestSeller.BestSellerViewHolder>() {
    private var bestseller:List<BestSeller> = listOf()

    var onItemclick:((BestSeller)->Unit)?= null

        fun getBestSellerList(bestSellerList: List<BestSeller>){
         bestseller = bestSellerList
        notifyDataSetChanged()
        }

    class BestSellerViewHolder (var binding:ImageItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(bestSeller: BestSeller) {
            binding.price.text = bestSeller.discountPrice.toString()
            binding.nameProduct.text = bestSeller.title
        }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val view = BestSellerViewHolder(ImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        return view
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val bestsellerList = bestseller[position]
        holder.bind(bestsellerList)

        holder.binding.price.text = bestsellerList.discountPrice.toString()
        holder.binding.nameProduct.text = bestsellerList.title.toString()
        holder.binding.priceNewe.text = "$${bestsellerList.priceWithoutDiscount.toString()}"
        holder.binding.view.setOnClickListener {
                    onItemclick?.invoke(bestsellerList)
        }
        val imagefolover:Boolean = bestsellerList.isFavorites
        if (imagefolover == true) {
                 holder.binding.imagefolover.setImageResource(R.drawable.folovertrue)
            }else
            {
                holder.binding.imagefolover.setImageResource(R.drawable.foloverfalse)
            }
        Glide.with(context)
            .load(bestsellerList.picture)
            .placeholder(R.drawable.smart)
            .into(holder.binding.imageProduct)

    }

    override fun getItemCount(): Int =bestseller.size
}







