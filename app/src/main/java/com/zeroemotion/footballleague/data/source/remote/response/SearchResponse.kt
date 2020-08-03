package com.zeroemotion.footballleague.data.source.remote.response

import com.zeroemotion.footballleague.data.model.Match

data class SearchResponse(
    val event: ArrayList<Match>
)