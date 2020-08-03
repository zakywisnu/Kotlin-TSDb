package com.zeroemotion.footballleague.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.zeroemotion.footballleague.data.model.Match

data class MatchResponse(
    @SerializedName("events")
    val events: ArrayList<Match>
)