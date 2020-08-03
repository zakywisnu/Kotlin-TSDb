package com.zeroemotion.footballleague.ui.match.next

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.data.model.Match
import com.zeroemotion.footballleague.databinding.MatchNextItemBinding
import com.zeroemotion.footballleague.ui.detailleague.DetailLeagueFragmentDirections
import com.zeroemotion.footballleague.util.CustomOnClick
import com.zeroemotion.footballleague.util.FormatDate

class MatchNextAdapter(private val matchList: ArrayList<Match>) :
    RecyclerView.Adapter<MatchNextAdapter.MatchNextViewHolder>(), CustomOnClick {

    fun updateNextMatch(newMatch: List<Match>?) {
        matchList.clear()
        newMatch?.let { matchList.addAll(it) }
        notifyDataSetChanged()
    }

    fun clear() {
        matchList.clear()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchNextViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MatchNextItemBinding>(
            inflater,
            R.layout.match_next_item,
            parent,
            false
        )
        return MatchNextViewHolder(
            view
        )
    }

    override fun getItemCount() = matchList.size

    override fun onBindViewHolder(holder: MatchNextViewHolder, position: Int) {
        val newDate = if (matchList[position].strDate != null) {
            FormatDate.formatCurrentDate(matchList[position].strDate.toString())
        } else {
            ""
        }
        val newTime = matchList[position].strTime?.let { FormatDate.formatCurrentTime(it) }
        holder.view.matchDate.text = newDate
        holder.view.matchTime.text = newTime
        holder.view.matchNext = matchList[position]
        holder.view.listener = this
    }

    override fun onViewClicked(v: View) {
        for (event in matchList) {
            if (v.tag == event.idEvent) {
                val action = DetailLeagueFragmentDirections.actionDetailMatch(event)
                Navigation.findNavController(v).navigate(action)
            }
        }
    }

    class MatchNextViewHolder(var view: MatchNextItemBinding) : RecyclerView.ViewHolder(view.root) {

    }


}