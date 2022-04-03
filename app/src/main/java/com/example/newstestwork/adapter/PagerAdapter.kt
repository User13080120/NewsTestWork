package com.example.newstestwork.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newstestwork.fragment.NewsFragment


class PagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> NewsFragment.newInstance("strories")
            1 -> NewsFragment.newInstance("video")
            else -> NewsFragment.newInstance("favourites")
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

}