package com.carolmusyoka.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.carolmusyoka.movieapp.popular.adapter.PageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     lateinit var viewPager: ViewPager2
     lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

       tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        val adapter = PageAdapter(supportFragmentManager,lifecycle)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab,position ->
            when(position){
                0 ->{
                    tab.text = "Movies"
                }
                1 ->{
                    tab.text = "Series"
                }
                2 ->{
                    tab.text = "My List"
                }
            }

        }.attach()


    }
}