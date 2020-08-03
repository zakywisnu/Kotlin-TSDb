package com.zeroemotion.footballleague.ui.detailmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.footballleague.data.model.Teams
import com.zeroemotion.footballleague.data.source.FootballRepository

class DetailMatchViewModel(
    private val repository: FootballRepository,
    private val idHomeTeam: String,
    private val idAwayTeam: String
) : ViewModel() {

    private fun getHomeRepo() = repository.getHomeBadge(idHomeTeam)
    fun getHomeBadge(): LiveData<Teams> = getHomeRepo()

    private fun getAwayRepo() = repository.getAwayBadge(idAwayTeam)
    fun getAwayBadge(): LiveData<Teams> = getAwayRepo()

}