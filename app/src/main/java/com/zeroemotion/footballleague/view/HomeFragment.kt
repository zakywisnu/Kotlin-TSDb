package com.zeroemotion.footballleague.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.adapter.LeagueAdapter
import com.zeroemotion.footballleague.databinding.FragmentHomeBinding
import com.zeroemotion.footballleague.viewmodel.HomeViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.league_item.*
import kotlinx.android.synthetic.main.league_item.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var leagueAdapter = LeagueAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentHomeBinding
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        rvLeague.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = leagueAdapter
        }
        viewModel.fetchLeague()



        leagueRefresh.setOnRefreshListener {
            rvLeague.visibility = View.GONE
            leagueLoading.visibility = View.GONE
            disposable.dispose()
            leagueAdapter.clearLeagueList()
            viewModel.fetchLeague()
            leagueRefresh.isRefreshing = false
            Toast.makeText(context,"Refreshing", Toast.LENGTH_SHORT).show()
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.listLeague.observe(viewLifecycleOwner, Observer { league ->
            league?.let {
                rvLeague.visibility = View.VISIBLE
                leagueAdapter.updateLeagueList(league)
            }
        })

        viewModel.listLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                leagueLoading.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    rvLeague.visibility = View.GONE
                    leagueError.visibility = View.GONE
                }
            }
        })

        viewModel.listError.observe(viewLifecycleOwner, Observer { listErr ->
            listErr?.let {
                leagueError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

    }

}
