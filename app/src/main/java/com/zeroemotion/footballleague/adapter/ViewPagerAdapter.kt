package com.zeroemotion.footballleague.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.view.NextFragment
import com.zeroemotion.footballleague.view.PrevFragment

class ViewPagerAdapter(fm: FragmentManager, context:Context): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val strTab0 = context.getString(R.string.str_prev_match)
    private val strTab1 = context.getString(R.string.str_next_match)
    private val pages = listOf(PrevFragment(), NextFragment())

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> strTab0
            else -> strTab1
        }
    }

}