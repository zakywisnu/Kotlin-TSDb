package com.zeroemotion.footballleague.ui.league

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.data.source.NetworkState
import com.zeroemotion.footballleague.databinding.FragmentHomeBinding
import com.zeroemotion.footballleague.util.CustomUI.showProgress
import com.zeroemotion.footballleague.util.CustomUI.showProgressDialog
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    //    private lateinit var viewModel: HomeViewModel
    private val viewModel: HomeViewModel by viewModel()
    private var leagueAdapter =
        LeagueAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentHomeBinding
    private lateinit var tvProgress: TextView
    private lateinit var strProgress: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showProgressDialog(context)
        tvProgress = showProgress.findViewById(R.id.tv_progress_cust)
        strProgress = getString(R.string.str_progress)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvLeague.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = leagueAdapter
            setHasFixedSize(true)
        }

        leagueRefresh.setOnRefreshListener {
            rvLeague.visibility = View.GONE
            leagueLoading.visibility = View.GONE
            leagueAdapter.clearList()
            viewModel.getLeague()
            leagueRefresh.isRefreshing = false
            Toast.makeText(context, "Refreshing", Toast.LENGTH_SHORT).show()
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getLeague().observe(viewLifecycleOwner, Observer { league ->
            league?.let {
                rvLeague.visibility = View.VISIBLE
                leagueAdapter.updateLeagueList(it.leagues)
            }
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                if (it == NetworkState.LOADING) {
                    showLoading()
                    Log.i("State", "Loading")
                } else if (it == NetworkState.ERROR) {
                    leagueError.visibility = View.VISIBLE
                    hideLoading()
                    Log.i("State", "Error")
                } else {
                    hideLoading()
                    leagueError.visibility = View.GONE
                    Log.i("State", "All Loaded")
                }
            }
        })

    }

    fun showLoading() {
        tvProgress.text = strProgress
        showProgress.show()
    }

    fun hideLoading() {

        showProgress.dismiss()
    }

    override fun onResume() {
        super.onResume()
        leagueAdapter.clearList()
    }
}
