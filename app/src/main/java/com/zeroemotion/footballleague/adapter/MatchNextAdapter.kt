package com.zeroemotion.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.databinding.MatchNextItemBinding
import com.zeroemotion.footballleague.model.Match
import com.zeroemotion.footballleague.util.CustomOnClick

class MatchNextAdapter(val matchList: ArrayList<Match>): RecyclerView.Adapter<MatchNextAdapter.MatchNextViewHolder>(), CustomOnClick{

    fun updateNextMatch(newMatch: List<Match>){
        matchList.clear()
        matchList.addAll(newMatch)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchNextViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MatchNextItemBinding>(inflater, R.layout.match_next_item, parent,false)
        return MatchNextViewHolder(view)
    }

    override fun getItemCount()= matchList.size

    override fun onBindViewHolder(holder: MatchNextViewHolder, position: Int) {
        holder.view.matchNext = matchList[position]
        holder.view.listener = this
    }
    override fun onViewClicked(v: View) {
    }
    class MatchNextViewHolder(var view: MatchNextItemBinding): RecyclerView.ViewHolder(view.root) {

    }



}