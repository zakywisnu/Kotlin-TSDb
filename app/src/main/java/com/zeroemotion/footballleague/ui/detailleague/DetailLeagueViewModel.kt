package com.zeroemotion.footballleague.ui.detailleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.footballleague.data.model.League
import com.zeroemotion.footballleague.data.source.FootballRepository

class DetailLeagueViewModel(
    private val footballRepository: FootballRepository,
    private val idLeague: String
) : ViewModel() {
    private fun getDetailRepo() = footballRepository.getDetailLeague(idLeague)
    fun getLeagueDetail(): LiveData<League> = getDetailRepo()

}