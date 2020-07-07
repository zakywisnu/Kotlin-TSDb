package com.zeroemotion.footballleague.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.viewmodel.DetailLeagueViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : Fragment() {

    private lateinit var viewModel: DetailLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_league, container, false)
    }

}
