package com.zeroemotion.footballleague.data.source.remote

import com.zeroemotion.footballleague.data.model.Match
import com.zeroemotion.footballleague.data.source.remote.response.LeagueResponse
import com.zeroemotion.footballleague.data.source.remote.response.MatchResponse
import com.zeroemotion.footballleague.data.source.remote.response.SearchResponse
import com.zeroemotion.footballleague.data.source.remote.response.TeamsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?): Observable<MatchResponse>

    @GET("eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String?): Observable<MatchResponse>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") id: String?): Observable<LeagueResponse>

    @GET("all_leagues.php?q=soccer")
    fun getAllLeague(): Observable<LeagueResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") id: String): Observable<Match>

    @GET("searchevents.php")
    fun getSearchMatch(@Query("e") query: String?): Observable<SearchResponse>

    @GET("lookupteam.php")
    fun getDetailTeamHome(@Query("id") id: String?): Observable<TeamsResponse>

    @GET("lookupteam.php")
    fun getDetailTeamAway(@Query("id") id: String?): Observable<TeamsResponse>


}