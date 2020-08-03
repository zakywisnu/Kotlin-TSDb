package com.zeroemotion.footballleague.ui.match.previous

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.data.model.Match
import com.zeroemotion.footballleague.databinding.MatchPrevItemBinding
import com.zeroemotion.footballleague.ui.detailleague.DetailLeagueFragmentDirections
import com.zeroemotion.footballleague.util.CustomOnClick
import com.zeroemotion.footballleague.util.FormatDate

class MatchPrevAdapter(val listPrev: ArrayList<Match>) :
    RecyclerView.Adapter<MatchPrevAdapter.MatchPrevViewHolder>(), CustomOnClick {

    fun updatePrevMatch(newPrevList: List<Match>?) {
        listPrev.clear()
        newPrevList?.let { listPrev.addAll(it) }
        notifyDataSetChanged()
    }

    fun clear() {
        listPrev.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPrevViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MatchPrevItemBinding>(
            inflater,
            R.layout.match_prev_item,
            parent,
            false
        )
        return MatchPrevViewHolder(
            view
        )
    }

    override fun getItemCount() = listPrev.size

    override fun onBindViewHolder(holder: MatchPrevViewHolder, position: Int) {
        val newDate = if (listPrev[position].strDate != null) {
            FormatDate.formatCurrentDate(listPrev[position].strDate.toString())
        } else {
            ""
        }
        val newTime = listPrev[position].strTime?.let { FormatDate.formatCurrentTime(it) }
        Log.i("time", "old Time: " + listPrev[position].strTime)
        Log.i("time", "new Time: $newTime")
        holder.view.matchDate.text = newDate
        holder.view.matchPrevTime.text = newTime
        holder.view.matchPrev = listPrev[position]
        holder.view.listener = this
    }

    override fun onViewClicked(v: View) {
        for (event in listPrev) {
            if (v.tag == event.idEvent) {
                val action = DetailLeagueFragmentDirections.actionDetailMatch(event)
                Navigation.findNavController(v).navigate(action)
            }
        }
    }

    class MatchPrevViewHolder(var view: MatchPrevItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

}