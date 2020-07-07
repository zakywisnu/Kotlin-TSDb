package com.zeroemotion.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    @SerializedName("idLeague")
    val idLeague: String?,
    val strEvent: String?,
    val idEvent: String?,
    val strLeague: String?,

    //Home
    val idHomeTeam: String?,
    val intHomeScore: String?,
    val strHomeTeam: String?,
    val strHomeGoalDetails: String?,
    val strHomeRedCards: String?,
    val strHomeYellowCards: String?,
    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupMidfield: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupSubstitutes: String?,

    //Away
    val idAwayTeam: String?,
    val strAwayTeam: String?,
    val intAwayScore: String?,
    val strAwayGoalDetails: String?,
    val strAwayRedCards: String?,
    val strAwayYellowCards: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupMidfield: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupSubstitutes: String?,

    val strDate: String?,
    val strTime: String?

): Parcelable