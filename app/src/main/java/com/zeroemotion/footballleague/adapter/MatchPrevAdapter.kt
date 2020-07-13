package com.zeroemotion.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.databinding.MatchPrevItemBinding
import com.zeroemotion.footballleague.model.Match
import com.zeroemotion.footballleague.util.CustomOnClick

class MatchPrevAdapter(val listPrev: ArrayList<Match>) :
    RecyclerView.Adapter<MatchPrevAdapter.MatchPrevViewHolder>(), CustomOnClick {

    fun updatePrevMatch(newPrevList: List<Match>){
        listPrev.addAll(newPrevList)
        notifyDataSetChanged()
    }

    fun clearPrevMatch(){
        listPrev.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPrevViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MatchPrevItemBinding>(inflater, R.layout.match_prev_item, parent, false)
        return MatchPrevViewHolder(view)
    }

    override fun getItemCount() = listPrev.size

    override fun onBindViewHolder(holder: MatchPrevViewHolder, position: Int) {
        holder.view.matchPrev = listPrev[position]
        holder.view.listener = this
    }

    override fun onViewClicked(v: View) {
        TODO("Not yet implemented")
    }

    class MatchPrevViewHolder(var view: MatchPrevItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

}