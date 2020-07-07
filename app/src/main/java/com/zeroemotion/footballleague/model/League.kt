package com.zeroemotion.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(

    @SerializedName("idLeague")
    val id: String?,
    @SerializedName("strLeague")
    val strLeague: String?,
    val strDivision: String?,
    val strCountry: String?,
    val strWebsite: String?,
    val strDescriptionEN: String?,
    val strBadge: String?,
    val strBanner: String?
): Parcelable