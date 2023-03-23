package com.example.ecommerceconcept.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ListItemCartBinding
import com.example.ecommerceconcept.domain.model.cart.Basket


class AdapterCart(val context: Context):RecyclerView.Adapter<AdapterCart.CartViewHolder>() {
    private var cartList:List<Basket> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setCartList(basket: List<Basket>){
        cartList = basket
        notifyDataSetChanged()
    }
    class CartViewHolder(var binding: ListItemCartBinding):RecyclerView.ViewHolder(binding.root) {
            fun bind(basket: Basket){
              binding.name.text = basket.title
            binding.price.text = "$${basket.price}.00"
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = CartViewHolder(ListItemCartBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    return view
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
      val cart = cartList[position]
        holder.bind(cart)
        holder.binding.name.text = cart.title
        holder.binding.price.text = "$${cart.price}.00"
        Glide.with(context)
            .load(cart.images)
            .placeholder(R.drawable.smart)
            .into(holder.binding.image)
    }
}