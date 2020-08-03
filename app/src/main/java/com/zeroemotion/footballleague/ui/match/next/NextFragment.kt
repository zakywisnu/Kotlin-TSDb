package com.zeroemotion.footballleague.ui.match.next

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
import com.zeroemotion.footballleague.databinding.FragmentNextBinding
import com.zeroemotion.footballleague.ui.detailleague.DetailLeagueFragment
import kotlinx.android.synthetic.main.fragment_next.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class NextFragment : Fragment() {

    private var idLeague = ""
    private lateinit var viewModel: NextMatchViewModel
    private lateinit var dataBinding: FragmentNextBinding
    private var nextAdapter = MatchNextAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_next, container, false)
        idLeague = DetailLeagueFragment.uuid
        viewModel = getViewModel { parametersOf(idLeague) }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNext.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = nextAdapter
        }

        nextRefresh.setOnRefreshListener {
            nextAdapter.clear()
            viewModel.getNext()
            nextRefresh.isRefreshing = false
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getNext().observe(viewLifecycleOwner, Observer { match ->
            rvNext.visibility = View.VISIBLE
            nextAdapter.updateNextMatch(match.events)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                nextLoading.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            }
        })
    }

}
