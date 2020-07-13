package com.zeroemotion.footballleague.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.databinding.LeagueItemBinding
import com.zeroemotion.footballleague.model.League
import com.zeroemotion.footballleague.util.CustomOnClick
import com.zeroemotion.footballleague.util.getProgressDrawable
import com.zeroemotion.footballleague.util.loadImage
import com.zeroemotion.footballleague.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.league_item.view.*

class LeagueAdapter (val leagueList: ArrayList<League>): RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>(), CustomOnClick{

    fun updateLeagueList(newLeague: List<League>){
        leagueList.clear()
        leagueList.addAll(newLeague)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<LeagueItemBinding>(inflater, R.layout.league_item,parent,false)
        return LeagueViewHolder(view)
    }

    override fun getItemCount() = leagueList.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.view.league = leagueList[position]
        holder.view.listener = this
        holder.view.imageLeagueItem.loadImage(
            leagueList[position].strBadge + "/preview",
            getProgressDrawable(holder.view.imageLeagueItem.context)
        )
    }

    override fun onViewClicked(v: View) {
        val idLiga = v.idLeague.text.toString().toInt()
        Log.i("idliga", "idLiga = $idLiga")
        val action = HomeFragmentDirections.actionDetailLeague()
        action.leagueId = idLiga
        Navigation.findNavController(v).navigate(action)
    }

    class LeagueViewHolder(var view: LeagueItemBinding): RecyclerView.ViewHolder(view.root) {

    }

}