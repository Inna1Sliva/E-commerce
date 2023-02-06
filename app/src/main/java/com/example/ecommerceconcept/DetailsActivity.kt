package com.example.ecommerceconcept

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceconcept.adapter.AdapterDetails
import com.example.ecommerceconcept.databinding.ActivityProductDetailsBinding
import com.example.ecommerceconcept.model.category.ProductDeteils
import com.example.ecommerceconcept.viewModel.MainViewModel
import com.example.ecommerceconcept.viewModel.NetworkViewModelFactory
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {
    @Inject
    lateinit var networkViewModelFactory: NetworkViewModelFactory

    @Inject
    lateinit var mainViewModel: MainViewModel
private lateinit var adapterDetails: AdapterDetails
    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as DaggerApplication).appComponetns.deteil(this)
        mainViewModel = ViewModelProvider(this, networkViewModelFactory).get(MainViewModel::class.java)

        binding.imageItem.apply {
            layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
            adapterDetails = AdapterDetails(this@DetailsActivity)
            adapter = adapterDetails
        }
        binding.apply {
            mainViewModel.AllDetails.observe(this@DetailsActivity){detail->
                detail.let {
                    nameProduct.text=it?.title
                    price.text = "$${it?.price}.00"
                }
            }
        }
        binding.closeFilter.setOnClickListener {
            val mActivity = Intent(this, MainActivity::class.java)
            startActivity(mActivity)
            finish()
        }
        binding.shopp.setOnClickListener {
            val aCart = Intent(this, CartActivity::class.java)
            startActivity(aCart)
            finish()
        }
        observerImage()



    }

    private fun observerImage() {
        mainViewModel.AllImage.observe(this){image->
         image?.let {
                adapterDetails.getDeteilList(it)
            }

        }
    }
}