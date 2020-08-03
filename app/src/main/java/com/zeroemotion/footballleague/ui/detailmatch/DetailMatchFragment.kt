package com.zeroemotion.footballleague.ui.detailmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.data.model.Match
import com.zeroemotion.footballleague.databinding.FragmentDetailMatchBinding
import com.zeroemotion.footballleague.util.getProgressDrawable
import com.zeroemotion.footballleague.util.loadImage
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment() {

    private lateinit var viewModel: DetailMatchViewModel
    private lateinit var dataBinding: FragmentDetailMatchBinding
    private var idHome = ""
    private var idAway = ""
    private var matches: Match? = null
    private val args: DetailMatchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_match, container, false)
        matches = args.idMatch
        idHome = matches!!.idHomeTeam.toString()
        idAway = matches!!.idAwayTeam.toString()
        viewModel = getViewModel { parametersOf(idHome, idAway) }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.match = matches
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getHomeBadge().observe(viewLifecycleOwner, Observer { home ->
            home?.let {
                dataBinding.imageHomeDetail.loadImage(
                    it.strTeamBadge,
                    getProgressDrawable(dataBinding.imageHomeDetail.context)
                )
                dataBinding.matchHomeBadge.loadImage(
                    it.strTeamBadge,
                    getProgressDrawable(dataBinding.imageHomeDetail.context)
                )
            }
        })
        viewModel.getAwayBadge().observe(viewLifecycleOwner, Observer { away ->
            away?.let {
                dataBinding.imageAwayDetail.loadImage(
                    it.strTeamBadge,
                    getProgressDrawable(dataBinding.imageAwayDetail.context)
                )
                dataBinding.matchAwayBadge.loadImage(
                    it.strTeamBadge,
                    getProgressDrawable(dataBinding.imageAwayDetail.context)
                )
            }
        })
    }

}
