package com.zeroemotion.footballleague.service

import com.zeroemotion.footballleague.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id:String?): Observable<MatchResponse>

    @GET("eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String?): Observable<MatchResponse>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") id: String?): Observable<LeagueResponse>

    @GET("all_leagues.php?q=soccer")
    fun getAllLeague(): Observable<LeagueResponse>

    @GET("searchevents.php")
    fun getSearchMatch(@Query("e") query:String?): Observable<SearchResponse>

    @GET("lookupteam.php")
    fun getDetailTeamHome(@Query("id") id: Int?): Observable<TeamsResponseHome>

    @GET("lookupteam.php")
    fun getDetailTeamAway(@Query("id") id: Int?): Observable<TeamsResponseAway>

}