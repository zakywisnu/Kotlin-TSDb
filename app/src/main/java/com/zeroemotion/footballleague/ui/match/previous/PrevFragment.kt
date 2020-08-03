package com.zeroemotion.footballleague.ui.match.previous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.data.source.NetworkState
import com.zeroemotion.footballleague.databinding.FragmentPrevBinding
import com.zeroemotion.footballleague.ui.detailleague.DetailLeagueFragment
import kotlinx.android.synthetic.main.fragment_prev.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class PrevFragment : Fragment() {

    private var idLeague = ""
    private lateinit var dataBinding: FragmentPrevBinding
    private lateinit var viewModel: PrevMatchViewModel
    private var prevAdapter = MatchPrevAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_prev, container, false)
        idLeague = DetailLeagueFragment.uuid
        viewModel = getViewModel { parametersOf(idLeague) }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPrev.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = prevAdapter
        }
        prevRefresh.setOnRefreshListener {
            prevAdapter.clear()
            viewModel.getPrev()
            prevRefresh.isRefreshing = false
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getPrev().observe(viewLifecycleOwner, Observer { match ->
            match?.let {
                rvPrev.visibility = View.VISIBLE
                prevAdapter.updatePrevMatch(it.events)
            }
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                prevLoading.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            }
        })
    }

}
