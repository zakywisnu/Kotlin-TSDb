package com.zeroemotion.footballleague.ui.detailleague

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.footballleague.R
import com.zeroemotion.footballleague.data.model.Match
import com.zeroemotion.footballleague.databinding.EventItemBinding
import com.zeroemotion.footballleague.util.CustomOnClick

class SearchAdapter(private val searchMatch: ArrayList<Match>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(), CustomOnClick {

    fun updateSearch(newSearch: List<Match>) {
        searchMatch.clear()
        searchMatch.addAll(newSearch)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<EventItemBinding>(inflater, R.layout.event_item, parent, false)
        return SearchViewHolder(
            view
        )
    }

    override fun getItemCount() = searchMatch.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.view.match = searchMatch[position]
        holder.view.listener = this
    }

    class SearchViewHolder(var view: EventItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onViewClicked(v: View) {
        for (event in searchMatch)
            if (v.tag == event.idEvent) {
                val action = DetailLeagueFragmentDirections.actionDetailMatch(event)
                Navigation.findNavController(v).navigate(action)
            }
    }
}