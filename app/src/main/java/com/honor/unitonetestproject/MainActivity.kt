package com.honor.unitonetestproject

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.akshay.library.CurveBottomBar
import com.honor.unitonetestproject.Adapter.SliderAdapter
import com.honor.unitonetestproject.Model.SliderItem
import kotlin.collections.ArrayList
import kotlin.math.abs


const  val NUM_PAGES = 1

class MainActivity : FragmentActivity() {

    private val curveBottomBar: CurveBottomBar? = null

    private lateinit var sliderItemList : ArrayList<SliderItem>
    private lateinit var sliderAdapter:SliderAdapter
    private lateinit var sliderHandeler : Handler
    private lateinit var sliderRun : Runnable

    private lateinit var mPager: ViewPager2
    private lateinit var img_home:ImageView
    private lateinit var img_person:ImageView
    private lateinit var img_smile:ImageView
    private lateinit var img_gift:ImageView
    private lateinit var tv_home:TextView
    private lateinit var tv_gift:TextView
    private lateinit var tv_smile:TextView
    private lateinit var tv_person:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPager = findViewById(R.id.mPager)
        img_home = findViewById(R.id.img_home)
        img_person = findViewById(R.id.img_person)
        img_smile = findViewById(R.id.img_smile)
        img_gift = findViewById(R.id.img_gift)
        tv_home = findViewById(R.id.tv_home)
        tv_gift = findViewById(R.id.tv_gift)
        tv_smile = findViewById(R.id.tv_smile)
        tv_person = findViewById(R.id.tv_person)

        curveBottomBar?.setBottomBarColor(getResources().getColor(R.color.black));
        curveBottomBar?.setCurveRadius(52);

        img_home.setOnClickListener {

            img_home.setColorFilter(ContextCompat.getColor(this, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
            img_person.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_smile.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_gift.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);

            tv_home.setTextColor(Color.parseColor("#E41212"))
            tv_gift.setTextColor(Color.parseColor("#D0CCCC"))
            tv_smile.setTextColor(Color.parseColor("#D0CCCC"))
            tv_person.setTextColor(Color.parseColor("#D0CCCC"))

        }

        img_person.setOnClickListener {

            img_home.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_person.setColorFilter(ContextCompat.getColor(this, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
            img_smile.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_gift.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);


            tv_home.setTextColor(Color.parseColor("#D0CCCC"))
            tv_gift.setTextColor(Color.parseColor("#D0CCCC"))
            tv_smile.setTextColor(Color.parseColor("#D0CCCC"))
            tv_person.setTextColor(Color.parseColor("#E41212"))

        }

        img_smile.setOnClickListener {

            img_home.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_person.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_smile.setColorFilter(ContextCompat.getColor(this, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
            img_gift.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);

            tv_home.setTextColor(Color.parseColor("#D0CCCC"))
            tv_gift.setTextColor(Color.parseColor("#D0CCCC"))
            tv_smile.setTextColor(Color.parseColor("#E41212"))
            tv_person.setTextColor(Color.parseColor("#D0CCCC"))

        }
        img_gift.setOnClickListener {

            img_home.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_person.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_smile.setColorFilter(ContextCompat.getColor(this, R.color.tint), android.graphics.PorterDuff.Mode.SRC_IN);
            img_gift.setColorFilter(ContextCompat.getColor(this, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);


            tv_home.setTextColor(Color.parseColor("#D0CCCC"))
            tv_gift.setTextColor(Color.parseColor("#E41212"))
            tv_smile.setTextColor(Color.parseColor("#D0CCCC"))
            tv_person.setTextColor(Color.parseColor("#D0CCCC"))
        }


        sliderItem()
        
        itemSliderView()


    }

    private fun sliderItem() {

        sliderItemList = ArrayList()
        sliderAdapter = SliderAdapter(mPager,sliderItemList)
        mPager.adapter = sliderAdapter
        mPager.clipToPadding = false
        mPager.clipChildren = false
        mPager.offscreenPageLimit = 3
        mPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosrPageTner = CompositePageTransformer()
        comPosrPageTner.addTransformer(MarginPageTransformer(40))
        comPosrPageTner.addTransformer { page, position ->

            val r:Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        mPager.setPageTransformer(comPosrPageTner)
        sliderHandeler = Handler()
        sliderRun = Runnable {
            mPager.currentItem = mPager.currentItem +1

        }
        mPager.registerOnPageChangeCallback(
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
        sliderHandeler.postDelayed(sliderRun,2000)

    }


    private fun itemSliderView() {

        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.personphoto))

    }
}