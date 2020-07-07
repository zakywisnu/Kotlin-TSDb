package com.zeroemotion.footballleague.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.viewmodel.MatchViewModel

/**
 * A simple [Fragment] subclass.
 */
class NextFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next, container, false)
    }

}
