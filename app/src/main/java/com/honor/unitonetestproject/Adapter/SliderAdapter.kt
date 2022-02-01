package com.honor.unitonetestproject.Adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.honor.unitonetestproject.Model.SliderItem
import com.honor.unitonetestproject.R

class SliderAdapter (
   val viewpager : ViewPager2 , val imageList :ArrayList<SliderItem>
    ):RecyclerView.Adapter<SliderAdapter.SliderViewHolder>()
   {

    inner class SliderViewHolder(var v:View) :RecyclerView.ViewHolder(v){
        val img  = v.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.slid_item,parent,false)
        return SliderViewHolder(v)


    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {

        val listImg = imageList[position]
        holder.img.setImageResource(listImg.img)
        if (position == imageList.size - 2){
            viewpager.post(run)
        }

    }

    val run = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = imageList.size

}