package com.honor.unitonetestproject.Ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.honor.unitonetestproject.Adapter.SliderAdapter
import com.honor.unitonetestproject.Model.SliderItem
import com.honor.unitonetestproject.R
import com.honor.unitonetestproject.databinding.ActivityMainBinding
import kotlin.math.abs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Main_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var sliderItemList : ArrayList<SliderItem>
    private lateinit var sliderAdapter:SliderAdapter
    private lateinit var sliderHandeler : Handler
    private lateinit var sliderRun : Runnable
    private lateinit var mPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_main_, container, false)
        mPager = view.findViewById(R.id.mPager);

        sliderItem()
        itemSliderView()


        return view
    }



    private fun sliderItem() {

        mPager

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
            object : ViewPager2.OnPageChangeCallback(){

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
        sliderItemList.add(SliderItem(R.drawable.person_test_2))
        sliderItemList.add(SliderItem(R.drawable.main_test_photo))


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Main_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Main_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}