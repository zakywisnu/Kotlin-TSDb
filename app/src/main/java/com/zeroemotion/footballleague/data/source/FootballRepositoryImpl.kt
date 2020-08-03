package com.zeroemotion.footballleague.data.source

import androidx.lifecycle.LiveData
import com.zeroemotion.footballleague.data.model.League
import com.zeroemotion.footballleague.data.model.Teams
import com.zeroemotion.footballleague.data.source.remote.RemoteDataSource
import com.zeroemotion.footballleague.data.source.remote.response.LeagueResponse
import com.zeroemotion.footballleague.data.source.remote.response.MatchResponse
import com.zeroemotion.footballleague.data.source.remote.response.SearchResponse

class FootballRepositoryImpl(private val remoteDataSource: RemoteDataSource) : FootballRepository {
    override fun getAllLeague(): LiveData<LeagueResponse> {
        remoteDataSource.getAllLeagues()
        return remoteDataSource.leagueResponse
    }

    override fun getDetailLeague(id: String): LiveData<League> {
        remoteDataSource.getDetailLeague(id)
        return remoteDataSource.detailLeague
    }

    override fun getHomeBadge(id: String): LiveData<Teams> {
        remoteDataSource.getHomeBadge(id)
        return remoteDataSource.homeBadge
    }

    override fun getAwayBadge(id: String): LiveData<Teams> {
        remoteDataSource.getAwayBadge(id)
        return remoteDataSource.awayBadge
    }

    override fun getPrevMatch(id: String): LiveData<MatchResponse> {
        remoteDataSource.getPrevMatch(id)
        return remoteDataSource.prevResponse
    }

    override fun getNextMatch(id: String): LiveData<MatchResponse> {
        remoteDataSource.getNextMatch(id)
        return remoteDataSource.nextResponse
    }

    override fun getSearch(query: String?): LiveData<SearchResponse> {
        remoteDataSource.getSearchMatch(query)
        return remoteDataSource.searchResponse
    }

    override fun getNetworkState(): LiveData<NetworkState> {
        return remoteDataSource.networkState
    }


}