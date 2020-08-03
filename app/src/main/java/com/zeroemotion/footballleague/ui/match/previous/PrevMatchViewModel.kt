package com.zeroemotion.footballleague.ui.match.previous

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.footballleague.data.source.FootballRepository
import com.zeroemotion.footballleague.data.source.NetworkState
import com.zeroemotion.footballleague.data.source.remote.response.MatchResponse

class PrevMatchViewModel(private val repository: FootballRepository, private val idLeague: String) :
    ViewModel() {

    private fun getPrevRepo() = repository.getPrevMatch(idLeague)
    fun getPrev(): LiveData<MatchResponse> = getPrevRepo()

    val networkState: LiveData<NetworkState> =
        repository.getNetworkState()

}