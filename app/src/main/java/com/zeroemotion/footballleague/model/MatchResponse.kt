package com.zeroemotion.footballleague.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val events: ArrayList<Match>
)