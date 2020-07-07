package com.zeroemotion.footballleague.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.footballleague.model.*
import com.zeroemotion.footballleague.service.FootballService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MatchViewModel(application: Application): BaseViewModel(application){

    val nextMatch = MutableLiveData<ArrayList<Match>>()
    val prevMatch = MutableLiveData<ArrayList<Match>>()
    private val disposable = CompositeDisposable()
    private val footballService = FootballService()
    val nextLoading = MutableLiveData<Boolean>()
    val prevLoading = MutableLiveData<Boolean>()
    val teamHomeBadge = MutableLiveData<ArrayList<Teams>>()
    val teamAwayBadge = MutableLiveData<ArrayList<Teams>>()

    fun fetchNextMatch(idLeague: String?){
        nextLoading.value = true
        disposable.add(
            footballService.getNextMatch(idLeague)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MatchResponse>(){
                    override fun onComplete() {
                        nextLoading.value = false
                    }

                    override fun onNext(match: MatchResponse) {
                        nextMatch.value = match.events
                        nextLoading.value = false

                    }

                    override fun onError(e: Throwable) {
                        nextLoading.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    fun fetchPrevMatch(idLeague: String?){
        prevLoading.value = true
        disposable.add(
            footballService.getPrevMatch(idLeague)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MatchResponse>(){
                    override fun onComplete() {
                        prevLoading.value = false
                    }

                    override fun onNext(match: MatchResponse) {
                        prevMatch.value = match.events
                        prevLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        prevLoading.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    fun fetchHomeBadge(idTeam: Int?){
        disposable.add(
            footballService.getDetailHome(idTeam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TeamsResponseHome>(){
                    override fun onComplete() {
                    }

                    override fun onNext(team: TeamsResponseHome) {
                        teamHomeBadge.value = team.teams
                    }

                    override fun onError(e: Throwable) {
                    }

                })
        )
    }

    fun fetchAwayBadge(idTeam: Int?){
        disposable.add(
            footballService.getDetailAway(idTeam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TeamsResponseAway>(){
                    override fun onComplete() {
                    }

                    override fun onNext(team: TeamsResponseAway) {
                        teamAwayBadge.value = team.teams
                    }

                    override fun onError(e: Throwable) {
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}