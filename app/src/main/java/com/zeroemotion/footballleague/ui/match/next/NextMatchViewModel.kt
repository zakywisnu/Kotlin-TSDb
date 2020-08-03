package com.zeroemotion.footballleague.ui.match.next

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.footballleague.data.source.FootballRepository
import com.zeroemotion.footballleague.data.source.NetworkState
import com.zeroemotion.footballleague.data.source.remote.response.MatchResponse

class NextMatchViewModel(private val repository: FootballRepository, private val idLeague: String) :
    ViewModel() {

    private fun getNextRepo() = repository.getNextMatch(idLeague)
    fun getNext(): LiveData<MatchResponse> = getNextRepo()


    val networkState: LiveData<NetworkState> =
        repository.getNetworkState()

}