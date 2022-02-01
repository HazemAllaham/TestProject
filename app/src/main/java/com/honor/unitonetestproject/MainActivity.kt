package com.honor.unitonetestproject

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.akshay.library.CurveBottomBar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.honor.unitonetestproject.Adapter.SliderAdapter
import com.honor.unitonetestproject.Model.SliderItem
import com.honor.unitonetestproject.databinding.ActivityMainBinding
import kotlin.collections.ArrayList
import kotlin.math.abs


const  val NUM_PAGES = 1

class MainActivity : FragmentActivity() {


    private lateinit var sliderItemList : ArrayList<SliderItem>
    private lateinit var sliderAdapter:SliderAdapter
    private lateinit var sliderHandeler : Handler
    private lateinit var sliderRun : Runnable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

         binding.bottomNavigationView.background = null
         binding.bottomNavigationView.menu.getItem(2).isEnabled = true

        sliderItem()
        itemSliderView()


    }


    private fun sliderItem() {

        sliderItemList = ArrayList()
        sliderAdapter = SliderAdapter(binding.mPager,sliderItemList)
        binding.mPager.adapter = sliderAdapter
        binding.mPager.clipToPadding = false
        binding.mPager.clipChildren = false
        binding.mPager.offscreenPageLimit = 3
        binding.mPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosrPageTner = CompositePageTransformer()
        comPosrPageTner.addTransformer(MarginPageTransformer(40))
        comPosrPageTner.addTransformer { page, position ->

            val r:Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        binding.mPager.setPageTransformer(comPosrPageTner)
        sliderHandeler = Handler()
        sliderRun = Runnable {
            binding.mPager.currentItem = binding.mPager.currentItem +1

        }
        binding.mPager.registerOnPageChangeCallback(
            object :ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandeler.removeCallbacks(sliderRun)
                    sliderHandeler.postDelayed(sliderRun,90000)
                }
            }

        )

    }

    override fun onResume() {
        super.onResume()
        sliderHandeler.removeCallbacks(sliderRun)

    }

    override fun onPause() {
        super.onPause()
        sliderHandeler.postDelayed(sliderRun,200000)

    }


    private fun itemSliderView() {

        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.personphoto))

    }
}

