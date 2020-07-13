package com.zeroemotion.footballleague.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.footballleague.model.League
import com.zeroemotion.footballleague.model.LeagueResponse
import com.zeroemotion.footballleague.service.FootballService
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.function.Function


class HomeViewModel(application: Application) : BaseViewModel(application) {

    val listLoading = MutableLiveData<Boolean>()
    val listLeague = MutableLiveData<List<League>>()
    val listError = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val footballService = FootballService()

    fun fetchLeague() {
        listLoading.value = true
        disposable.add(
            footballService.getAllLeague()
                .flatMap { list: LeagueResponse ->
                    Observable.fromIterable(list.leagues)
                }
                .filter { league: League -> league.strSport == "Soccer" }
                .flatMap { league: League ->
                    footballService.getDetailLeague(league.idLeague)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listLeague.value = it.leagues
                    listLoading.value = false
                    listError.value = false
                }, {
                    listLoading.value = false
                    listError.value = true
                    disposable.clear()
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}