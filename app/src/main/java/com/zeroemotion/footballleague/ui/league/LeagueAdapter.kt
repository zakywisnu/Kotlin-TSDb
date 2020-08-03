package com.zeroemotion.footballleague.ui.league

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.data.model.League
import com.zeroemotion.footballleague.databinding.LeagueItemBinding
import com.zeroemotion.footballleague.util.CustomOnClick
import com.zeroemotion.footballleague.util.getProgressDrawable
import com.zeroemotion.footballleague.util.loadImage
import kotlinx.android.synthetic.main.league_item.view.*

class LeagueAdapter(val leagueList: ArrayList<League>) :
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>(), CustomOnClick {

    private val limit = 10
    fun updateLeagueList(newLeague: List<League>) {
        leagueList.addAll(newLeague)
        notifyDataSetChanged()
    }

    fun clearList() {
        leagueList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<LeagueItemBinding>(
            inflater,
            R.layout.league_item,
            parent,
            false
        )
        return LeagueViewHolder(
            view
        )
    }


    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.view.league = leagueList[position]
        holder.view.listener = this
        holder.view.imageLeagueItem.loadImage(
            leagueList[position].strBadge + "/preview",
            getProgressDrawable(holder.view.imageLeagueItem.context)
        )
    }

    override fun getItemCount() = leagueList.size

    override fun onViewClicked(v: View) {
        val idLiga = v.idLeague.text.toString()
        Log.i("idliga", "idLiga = $idLiga")
        val action = HomeFragmentDirections.actionDetailLeague()
        action.leagueId = idLiga
        Navigation.findNavController(v).navigate(action)
    }

    class LeagueViewHolder(var view: LeagueItemBinding) : RecyclerView.ViewHolder(view.root)
}