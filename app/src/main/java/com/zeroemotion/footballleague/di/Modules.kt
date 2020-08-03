package com.zeroemotion.footballleague.di

import com.zeroemotion.footballleague.data.source.FootballRepository
import com.zeroemotion.footballleague.data.source.FootballRepositoryImpl
import com.zeroemotion.footballleague.data.source.remote.RemoteDataSource
import com.zeroemotion.footballleague.ui.detailleague.DetailLeagueViewModel
import com.zeroemotion.footballleague.ui.detailmatch.DetailMatchViewModel
import com.zeroemotion.footballleague.ui.league.HomeViewModel
import com.zeroemotion.footballleague.ui.match.next.NextMatchViewModel
import com.zeroemotion.footballleague.ui.match.previous.PrevMatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val module = module {
    viewModel { HomeViewModel(get()) }
    viewModel { (id: String) -> DetailLeagueViewModel(get(), id) }
    viewModel { (id: String) -> NextMatchViewModel(get(), id) }
    viewModel { (id: String) -> PrevMatchViewModel(get(), id) }
    viewModel { (idhome: String, idaway: String) -> DetailMatchViewModel(get(), idhome, idaway) }


    single<FootballRepository> { FootballRepositoryImpl(get()) }
    single { RemoteDataSource() }
}