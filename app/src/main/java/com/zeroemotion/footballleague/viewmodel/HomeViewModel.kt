package com.zeroemotion.footballleague.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.footballleague.model.League
import com.zeroemotion.footballleague.model.LeagueResponse
import com.zeroemotion.footballleague.service.FootballService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application): BaseViewModel(application){

    val listLoading = MutableLiveData<Boolean>()
    val listLeague = MutableLiveData<ArrayList<League>>()
    val listError = MutableLiveData<Boolean>()
    val imageLeague = MutableLiveData<ArrayList<League>>()
    private val disposable = CompositeDisposable()
    private val footballService = FootballService()

    fun fetchLeague(){
        listLoading.value = true
        disposable.add(
            footballService.getAllLeague()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<LeagueResponse>(){
                    override fun onComplete() {
                        listLoading.value = false
                    }

                    override fun onNext(league: LeagueResponse) {
                        listLoading.value = false
                        listLeague.value = league.leagues

                    }

                    override fun onError(e: Throwable) {
                        listLoading.value = false
                        listError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

    fun fetchLeagueBadge(id: String?){
        disposable.add(
            footballService.getDetailLeague(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<LeagueResponse>(){
                    override fun onComplete() {
                    }

                    override fun onNext(league: LeagueResponse) {
                        imageLeague.value = league.leagues
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