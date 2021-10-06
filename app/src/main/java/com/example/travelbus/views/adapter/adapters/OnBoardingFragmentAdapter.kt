package com.example.travelbus.views.adapter.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.travelbus.R

class OnBoardingFragmentAdapter(val context: Context) : PagerAdapter(){

    private lateinit var layoutInflater: LayoutInflater
    private val images = arrayOf(
        R.drawable.easy_booking,
        R.drawable.manage_trips,
        R.drawable.track_bus
    )

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.onboarding_custom_layout, null)

        val imageView = view.findViewById(R.id.onBoardingCustomImageView) as ImageView
        imageView.setImageResource(images[position])

        val viewPager = container as ViewPager
        viewPager.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }
}