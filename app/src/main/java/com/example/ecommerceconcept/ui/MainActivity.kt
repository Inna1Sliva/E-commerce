package com.example.ecommerceconcept.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceconcept.DaggerApplication
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.ui.adapter.AdapterBestSeller
import com.example.ecommerceconcept.ui.adapter.AdapterHotSale
import com.example.ecommerceconcept.ui.adapter.CategoryAdapter
import com.example.ecommerceconcept.databinding.ActivityMainBinding
import com.example.ecommerceconcept.domain.model.category.Category
import com.example.ecommerceconcept.domain.viewModel.MainViewModel
import com.example.ecommerceconcept.domain.viewModel.NetworkViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var networkViewModelFactory: NetworkViewModelFactory

    @Inject
    lateinit var mainViewModel: MainViewModel
    private var category: ArrayList<Category> = ArrayList()

    private lateinit var managerg: LinearLayoutManager
    private lateinit var manager: LinearLayoutManager

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var adapterBestSeller: AdapterBestSeller
    private lateinit var adapterHotSale: AdapterHotSale


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as DaggerApplication).appComponetns.inject(this)
        mainViewModel = ViewModelProvider(this, networkViewModelFactory).get(MainViewModel::class.java)

        binding.recHotSalles.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapterHotSale = AdapterHotSale(this@MainActivity)
            adapter = adapterHotSale
            setHasFixedSize(true)
        }
        binding.recBestSeler.apply {
            layoutManager =GridLayoutManager(this@MainActivity,2)
            adapterBestSeller = AdapterBestSeller(this@MainActivity)
            adapter = adapterBestSeller
            adapterBestSeller.onItemclick={
                val aDetails = Intent(this@MainActivity, DetailsActivity::class.java)
                startActivity(aDetails)
                finish()
            }
        }
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            categoryAdapter = CategoryAdapter(this@MainActivity, category)
            adapter = categoryAdapter
        }
       binding.badge.setOnClickListener {
            val cartActivity = Intent(this@MainActivity, CartActivity::class.java)
            startActivity(cartActivity)
       }


        observerBestSeller()
        getCategoryList()
        initViewModel()
         sizeCart()


        val filtermenu: ConstraintLayout = findViewById(R.id.filterMenu)

        managerg = GridLayoutManager(this, 2)
        manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //getHotSales()
        //adapterHotSale = AdapterHotSale(this)
        // binding.recBestSale.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        //binding.recBestSale.adapter = adapterHotSale





        binding.filter.setOnClickListener {
            filtermenu.visibility = View.VISIBLE
        }
    }

    private fun sizeCart() {
        mainViewModel.AllCartList.observe(this, Observer{
            var size:Int = 0
            it?.forEach{
                it->
                size =+ it.id
             binding.badge.setText(size.toString())
            }


        })
    }

    private fun observerBestSeller() {
        mainViewModel.AllBestSallerList.observe(this){bestSellerList->
            bestSellerList?.let {
                adapterBestSeller.getBestSellerList(it)
            }
        }
    }

    private fun initViewModel() {
        mainViewModel.AllHomeStoreList.observe(this) { homeStoreList ->
            homeStoreList?.let {
                adapterHotSale.setData(it)
            }

        }
    }


    private fun getCategoryList():ArrayList<Category> {
        category.add(Category(0,
            R.drawable.not_pressed,
            R.drawable.phone_color,getString(R.string.Phone)))
        category.add(Category(1,
            R.drawable.not_pressed,
            R.drawable.computer,getString(R.string.Computer)))
        category.add(Category(2,
            R.drawable.not_pressed,
            R.drawable.health,getString(R.string.Health)))
        category.add(Category(3,
            R.drawable.not_pressed,
            R.drawable.books_icon,getString(R.string.Books)))

   return category
    }
}
