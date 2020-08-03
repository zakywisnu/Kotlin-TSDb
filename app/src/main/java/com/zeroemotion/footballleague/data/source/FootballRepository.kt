package com.zeroemotion.footballleague.data.source

import androidx.lifecycle.LiveData
import com.zeroemotion.footballleague.data.model.League
import com.zeroemotion.footballleague.data.model.Teams
import com.zeroemotion.footballleague.data.source.remote.response.LeagueResponse
import com.zeroemotion.footballleague.data.source.remote.response.MatchResponse
import com.zeroemotion.footballleague.data.source.remote.response.SearchResponse

interface FootballRepository {
    fun getAllLeague(): LiveData<LeagueResponse>
    fun getDetailLeague(id: String): LiveData<League>
    fun getHomeBadge(id: String): LiveData<Teams>
    fun getAwayBadge(id: String): LiveData<Teams>
    fun getPrevMatch(id: String): LiveData<MatchResponse>
    fun getNextMatch(id: String): LiveData<MatchResponse>
    fun getSearch(query: String?): LiveData<SearchResponse>
    fun getNetworkState(): LiveData<NetworkState>
}