package com.zeroemotion.footballleague.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.footballleague.data.model.League
import com.zeroemotion.footballleague.data.model.Teams
import com.zeroemotion.footballleague.data.source.NetworkState
import com.zeroemotion.footballleague.data.source.remote.response.LeagueResponse
import com.zeroemotion.footballleague.data.source.remote.response.MatchResponse
import com.zeroemotion.footballleague.data.source.remote.response.SearchResponse
import com.zeroemotion.footballleague.data.source.remote.response.TeamsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.observable.ObservableFromIterable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class RemoteDataSource {
    private val footballService = FootballService()
    private val disposable = CompositeDisposable()

    private val _networkState: MutableLiveData<NetworkState> = MutableLiveData()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _tempHome: MutableLiveData<Teams> = MutableLiveData()
    val homeBadge: LiveData<Teams>
        get() = _tempHome
    private val _tempAway: MutableLiveData<Teams> = MutableLiveData()
    val awayBadge: LiveData<Teams>
        get() = _tempAway

    private val _leagueResponse: MutableLiveData<LeagueResponse> = MutableLiveData()
    val leagueResponse: LiveData<LeagueResponse>
        get() = _leagueResponse

    private val _detailLeague: MutableLiveData<League> = MutableLiveData()
    val detailLeague: LiveData<League>
        get() = _detailLeague

    private val _prevResponse: MutableLiveData<MatchResponse> = MutableLiveData()
    val prevResponse: LiveData<MatchResponse>
        get() = _prevResponse

    private val _nextResponse: MutableLiveData<MatchResponse> = MutableLiveData()
    val nextResponse: LiveData<MatchResponse>
        get() = _nextResponse

    private val _searchResponse: MutableLiveData<SearchResponse> = MutableLiveData()
    val searchResponse: LiveData<SearchResponse>
        get() = _searchResponse

    fun getAllLeagues() {
        _networkState.postValue(NetworkState.LOADING)
        disposable.add(
            footballService.getAllLeague()
                .flatMap { list: LeagueResponse ->
                    Observable.fromIterable(list.leagues)
                }
                .filter { league: League -> league.strSport == "Soccer" }
                .flatMap { league: League ->
                    footballService.getDetailLeague(league.idLeague)
                }
                .take(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<LeagueResponse>() {
                    override fun onComplete() {
                        _networkState.postValue(NetworkState.LOADED)
                    }

                    override fun onNext(t: LeagueResponse) {
                        _leagueResponse.postValue(t)
                    }

                    override fun onError(e: Throwable) {
                        _networkState.postValue(NetworkState.ERROR)
                        e.printStackTrace()
                        Log.e("error:", "Got an Error", e)
                    }

                })
        )
    }

    fun getDetailLeague(id: String) {
        _networkState.postValue(NetworkState.LOADING)
        disposable.add(
            footballService.getDetailLeague(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { list: LeagueResponse ->
                    ObservableFromIterable(list.leagues)
                }
                .subscribe({
                    _detailLeague.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                }, {
                    _networkState.postValue(NetworkState.ERROR)
                })
        )
    }

    fun getNextMatch(leagueId: String) {
        _networkState.postValue(NetworkState.LOADING)
        disposable.add(
            footballService.getNextMatch(leagueId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MatchResponse>() {
                    override fun onComplete() {
                        _networkState.postValue(NetworkState.LOADED)
                    }

                    override fun onNext(t: MatchResponse) {
                        _nextResponse.postValue(t)
                    }

                    override fun onError(e: Throwable) {
                        _networkState.postValue(NetworkState.ERROR)
                    }
                })
        )
    }

    fun getPrevMatch(leagueId: String) {
        _networkState.postValue(NetworkState.LOADING)
        Log.i("idLeague", "idLeague: $leagueId")
        disposable.add(
            footballService.getPrevMatch(leagueId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MatchResponse>() {
                    override fun onComplete() {
                        _networkState.postValue(NetworkState.LOADED)
                    }

                    override fun onNext(it: MatchResponse) {
                        _prevResponse.postValue(it)

                    }

                    override fun onError(e: Throwable) {
                        _networkState.postValue(NetworkState.ERROR)
                    }
                })
        )
    }

    fun getHomeBadge(id: String) {
        disposable.add(
            footballService.getDetailHome(id)
                .subscribeOn(Schedulers.io())
                .flatMap { list: TeamsResponse ->
                    ObservableFromIterable(list.teams)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _tempHome.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun getAwayBadge(id: String) {
        disposable.add(
            footballService.getDetailAway(id)
                .flatMap { list ->
                    ObservableFromIterable(list.teams)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _tempAway.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun getSearchMatch(query: String?) {
        _networkState.postValue(NetworkState.LOADING)
        disposable.add(
            footballService.getSearchMatch(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _searchResponse.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                }, {
                    _networkState.postValue(NetworkState.ERROR)
                })
        )
    }
}