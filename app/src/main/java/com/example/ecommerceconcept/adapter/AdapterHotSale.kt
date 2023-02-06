package com.example.ecommerceconcept.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.HotSalesItemBinding

import com.example.ecommerceconcept.model.category.HomeStore

class AdapterHotSale(val context: Context): RecyclerView.Adapter<AdapterHotSale.HotSaleViewHolder>() {
private var homeListstore:List<HomeStore> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(homeStore: List<HomeStore>){
      homeListstore =  homeStore
    notifyDataSetChanged()
}

    class HotSaleViewHolder(var binding: HotSalesItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(homeStore: HomeStore) {
          binding.description.text = homeStore.subtitle
          binding.nameProduct.text = homeStore.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSaleViewHolder {
        val view =HotSaleViewHolder(
            HotSalesItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        return view
    }

    override fun onBindViewHolder(holder: HotSaleViewHolder, position: Int) {
        val homestoreList = homeListstore[position]
        holder.bind(homestoreList)

         holder.binding.description.text = homestoreList.subtitle
        holder.binding.nameProduct.text = homestoreList.title.toString()
        Glide.with(context)
            .load(homestoreList.picture)
            .placeholder(R.drawable.smart)
            .into(holder.binding.imagePhone)
    }

    override fun getItemCount(): Int =homeListstore.size



}
