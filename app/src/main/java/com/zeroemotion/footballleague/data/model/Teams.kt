package com.zeroemotion.footballleague.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
    val id: Int?,
    val idTeam: String?,
    val strTeamBadge: String?,
    val strTeam: String?,
    val intFormedYear: String?,
    val strStadium: String?,
    val strDescriptionEN: String?
) : Parcelable