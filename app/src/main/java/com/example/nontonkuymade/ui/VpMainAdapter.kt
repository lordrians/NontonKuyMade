package com.example.nontonkuymade.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nontonkuymade.ui.favorite.FavoriteFragment
import com.example.nontonkuymade.ui.home.HomeFragment

class VpMainAdapter(
    private val fm: FragmentActivity
) : FragmentStateAdapter(fm){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return FavoriteFragment()
        }
        return HomeFragment()
    }
}