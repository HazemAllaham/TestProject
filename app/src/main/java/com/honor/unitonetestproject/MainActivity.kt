package com.honor.unitonetestproject

import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.honor.unitonetestproject.Adapter.SliderAdapter
import com.honor.unitonetestproject.Model.SliderItem
import com.honor.unitonetestproject.Ui.DoingGood_Fragment
import com.honor.unitonetestproject.Ui.Main_Fragment
import com.honor.unitonetestproject.Ui.MyPrezi_Fragment
import com.honor.unitonetestproject.Ui.Profile_Fragment
import com.honor.unitonetestproject.databinding.ActivityMainBinding
import kotlin.math.abs


const  val NUM_PAGES = 1

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    // listener For items
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.profile_item -> {
                    loadFragment(Profile_Fragment(), true)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.doing_good_item -> {
                    loadFragment(DoingGood_Fragment(), true)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.my_prezi_item -> {
                    loadFragment(MyPrezi_Fragment(), true)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.feed_item -> {
                    loadFragment(Main_Fragment(), true)
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = true


        // to Make the Home item Selectable directly
        val item: MenuItem = binding.bottomNavigationView.getMenu().findItem(R.id.feed_item)
        item.isChecked = true


        loadFragment(Main_Fragment(), true)
        binding.floatActionBtn.setOnClickListener {
            loadFragment(Main_Fragment(), true)
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

     fun loadFragment(fragment: Fragment, clearBackStackEntry: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}

