package com.zeroemotion.footballleague.service

import com.zeroemotion.footballleague.BuildConfig.BASE_URL
import com.zeroemotion.footballleague.model.*
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FootballService{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FootballApi::class.java)

    fun getNextMatch(id: String?): Observable<MatchResponse>{
        return retrofit.getNextMatch(id)
    }
    fun getPrevMatch(id: String?): Observable<MatchResponse>{
        return retrofit.getPrevMatch(id)
    }
    fun getDetailLeague(id: String?): Observable<LeagueResponse>{
        return retrofit.getDetailLeague(id)
    }
    fun getSearchMatch(e: String?): Observable<SearchResponse>{
        return retrofit.getSearchMatch(e)
    }
    fun getDetailHome(id: Int?): Observable<TeamsResponseHome>{
        return retrofit.getDetailTeamHome(id)
    }
    fun getDetailAway(id: Int?): Observable<TeamsResponseAway>{
        return retrofit.getDetailTeamAway(id)
    }
    fun getAllLeague(): Observable<LeagueResponse>{
        return retrofit.getAllLeague()
    }
}