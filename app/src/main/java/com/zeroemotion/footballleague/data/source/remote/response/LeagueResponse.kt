package com.zeroemotion.footballleague.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.zeroemotion.footballleague.data.model.League

data class LeagueResponse(
    @SerializedName("leagues")
    val leagues: List<League>
)