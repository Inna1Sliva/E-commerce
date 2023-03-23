package com.example.ecommerceconcept.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceconcept.DaggerApplication
import com.example.ecommerceconcept.ui.adapter.AdapterCart
import com.example.ecommerceconcept.databinding.ActivityCartBinding
import com.example.ecommerceconcept.domain.viewModel.MainViewModel
import com.example.ecommerceconcept.domain.viewModel.NetworkViewModelFactory
import javax.inject.Inject

class CartActivity : AppCompatActivity() {
    @Inject
    lateinit var networkViewModelFactory: NetworkViewModelFactory
    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var adapterCart: AdapterCart
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

      (application as DaggerApplication).appComponetns.cartActyvity(this)

        mainViewModel = ViewModelProvider(this, networkViewModelFactory).get(MainViewModel::class.java)

        binding.cartList.apply {
            layoutManager = LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            adapterCart = AdapterCart(this@CartActivity)
            adapter = adapterCart
        }
       binding.befor.setOnClickListener {
           val mAct = Intent(this@CartActivity, MainActivity::class.java)
           startActivity(mAct)
       }

        observerCartList()
        observerCart()
    }

    private fun observerCart() {
        mainViewModel.AllCart.observe(this){cart->
            cart.let {
                binding.delivery.text=it?.delivery
                binding.total.text = "$${it?.total.toString()} us"
            }

        }
    }

    private fun observerCartList() {
        mainViewModel.AllCartList.observe(this) { cartList ->
            cartList?.let {
                adapterCart.setCartList(it)
            }
        }
    }
}