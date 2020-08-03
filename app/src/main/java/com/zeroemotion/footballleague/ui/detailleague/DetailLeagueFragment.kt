package com.zeroemotion.footballleague.ui.detailleague

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.data.source.remote.FootballService
import com.zeroemotion.footballleague.databinding.FragmentDetailLeagueBinding
import com.zeroemotion.footballleague.ui.base.ViewPagerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_detail_league.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : Fragment() {

    companion object {
        var uuid = ""
    }

    private lateinit var viewModel: DetailLeagueViewModel
    private lateinit var dataBinding: FragmentDetailLeagueBinding
    private val args: DetailLeagueFragmentArgs by navArgs()
    private val searchAdapter =
        SearchAdapter(arrayListOf())
    private val disposable = CompositeDisposable()
    private val footballService = FootballService()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_league, container, false)
        uuid = args.leagueId
        viewModel = getViewModel { parametersOf(uuid) }
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        val pagerAdapter = ViewPagerAdapter(childFragmentManager)
        dataBinding.apply {
            detailViewPager.adapter = pagerAdapter
            tabLayout.setupWithViewPager(detailViewPager)
        }
        rvDetail.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.getLeagueDetail().observe(viewLifecycleOwner, Observer { league ->
            league?.let {
                dataBinding.league = it
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search Match"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchLoading.visibility = View.VISIBLE
                disposable.add(
                    footballService.getSearchMatch(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            searchAdapter.updateSearch(it.event)
                            rvDetail.visibility = View.VISIBLE
                            searchLoading.visibility = View.GONE
                        }, {
                            rvDetail.visibility = View.GONE
                        })
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }


}
