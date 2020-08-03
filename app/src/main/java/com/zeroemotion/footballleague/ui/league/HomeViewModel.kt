package com.zeroemotion.footballleague.ui.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.footballleague.data.source.FootballRepository
import com.zeroemotion.footballleague.data.source.NetworkState
import com.zeroemotion.footballleague.data.source.remote.response.LeagueResponse


class HomeViewModel(private val repository: FootballRepository) : ViewModel() {
    private fun getRepo() = repository.getAllLeague()
    fun getLeague(): LiveData<LeagueResponse> = getRepo()

    val networkState: LiveData<NetworkState> =
        repository.getNetworkState()
}