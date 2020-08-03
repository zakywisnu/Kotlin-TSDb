package com.zeroemotion.footballleague.data.source.remote

import com.zeroemotion.footballleague.BuildConfig.BASE_URL
import com.zeroemotion.footballleague.data.source.remote.response.LeagueResponse
import com.zeroemotion.footballleague.data.source.remote.response.MatchResponse
import com.zeroemotion.footballleague.data.source.remote.response.SearchResponse
import com.zeroemotion.footballleague.data.source.remote.response.TeamsResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FootballService {
    val requestInterceptor = Interceptor { chain ->
        // Interceptor take only one argument which is a lambda function so parenthesis can be omitted

        val url = chain.request()
            .url()
            .newBuilder()
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)   //explicitly return a value from whit @ annotation. lambda always returns the value of the last expression implicitly
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FootballApi::class.java)

    fun getNextMatch(id: String?): Observable<MatchResponse> {
        return retrofit.getNextMatch(id)
    }

    fun getPrevMatch(id: String?): Observable<MatchResponse> {
        return retrofit.getPrevMatch(id)
    }

    fun getDetailLeague(id: String?): Observable<LeagueResponse> {
        return retrofit.getDetailLeague(id)
    }

    fun getSearchMatch(e: String?): Observable<SearchResponse> {
        return retrofit.getSearchMatch(e)
    }

    fun getDetailHome(id: String?): Observable<TeamsResponse> {
        return retrofit.getDetailTeamHome(id)
    }

    fun getDetailAway(id: String?): Observable<TeamsResponse> {
        return retrofit.getDetailTeamAway(id)
    }

    fun getAllLeague(): Observable<LeagueResponse> {
        return retrofit.getAllLeague()
    }

}