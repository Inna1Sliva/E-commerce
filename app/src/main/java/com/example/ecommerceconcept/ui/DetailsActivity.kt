package com.example.ecommerceconcept.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ecommerceconcept.DaggerApplication
import com.example.ecommerceconcept.ui.adapter.AdapterDetails
import com.example.ecommerceconcept.databinding.ActivityProductDetailsBinding
import com.example.ecommerceconcept.domain.viewModel.MainViewModel
import com.example.ecommerceconcept.domain.viewModel.NetworkViewModelFactory
import javax.inject.Inject
import kotlin.math.abs

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
        setUpTransformer()

        (application as DaggerApplication).appComponetns.deteil(this)
        mainViewModel = ViewModelProvider(this, networkViewModelFactory).get(MainViewModel::class.java)

        binding.apply {
            //layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
            adapterDetails = AdapterDetails(this@DetailsActivity)
            vPage.adapter = adapterDetails
            vPage.offscreenPageLimit = 3
            vPage.clipToPadding = false
            vPage.clipChildren = false
            vPage.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
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

            startActivity(Intent(this, CartActivity::class.java))

        }
        observerImage()



    }

    private fun setUpTransformer() {
        val vTransformer =CompositePageTransformer()
        vTransformer.addTransformer(MarginPageTransformer(40))
        vTransformer.addTransformer { page, position ->
            val r= 1- abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        binding.vPage.setPageTransformer(vTransformer)
    }

    private fun observerImage() {
        mainViewModel.AllImage.observe(this){image->
         image?.let {
                adapterDetails.getDeteilList(it)
            }

        }
    }
}