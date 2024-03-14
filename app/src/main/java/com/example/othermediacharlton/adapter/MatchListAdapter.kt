package com.example.othermediacharlton.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.othermediacharlton.R
import com.example.othermediacharlton.data.model.FixturesDataModel
import com.example.othermediacharlton.data.model.Match

class MatchListAdapter: RecyclerView.Adapter<MatchListAdapter.MyViewHolder>()   {

    var matchListData = ArrayList<Match>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return matchListData.size
    }

    override fun onBindViewHolder(holder: MatchListAdapter.MyViewHolder, position: Int) {
        holder.bind(matchListData[position])
    }

    fun setData(fixturesDataModel: FixturesDataModel) {
        matchListData.clear()
        matchListData.addAll(fixturesDataModel.match)
        notifyDataSetChanged()
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val matchInfo = view.findViewById<TextView>(R.id.matchInfoDate)
        val venueShortName = view.findViewById<TextView>(R.id.venueShortName)
        val matchStatusIconPlayed = view.findViewById<ImageView>(R.id.matchStatusIconPlayed)
        val homeTeam = view.findViewById<TextView>(R.id.homeTeam)
        val homeTeamScore = view.findViewById<TextView>(R.id.homeTeamScore)
        val awayTeam = view.findViewById<TextView>(R.id.awayTeam)
        val awayTeamScore = view.findViewById<TextView>(R.id.awayTeamScore)
        val opponentImagePlaceHolder = view.findViewById<ImageView>(R.id.opponentImagePlaceHolder)
        val firstCalltoAction = view.findViewById<Button>(R.id.firstCalltoAction)
        val secondCalltoAction = view.findViewById<Button>(R.id.secondCalltoAction)


         fun bind(data: Match){
             matchInfo.text = data.matchInfo.date.toString()
             venueShortName.text = data.matchInfo.venue.shortName
             if (data.liveData.matchStatus == "Played"){
                 matchStatusIconPlayed.setImageResource(R.drawable.icon_match_status_played)
             } else {
                 // Set the default drawable for the ImageView
                 matchStatusIconPlayed.setImageResource(R.drawable.icon_match_status_fixture)
             }
             homeTeam.text = data.matchInfo.contestant[0].name

             if (data.liveData.matchStatus != "Fixtures"){
                 homeTeamScore.text = data.liveData.homeScore.toString()
                 awayTeamScore.text = data.liveData.awayScore.toString()
             } else {
                 homeTeamScore.text = ""
                 awayTeamScore.text = "18:00".toString()
             }

             awayTeam.text = data.matchInfo.contestant[1].name


             val url = data.matchInfo.contestant[0].crest.uri1x
             if(data.matchInfo.contestant[0].code != "CHA" &&
                 data.matchInfo.contestant[1].code != "CHA"){
                 Glide.with(opponentImagePlaceHolder)
                     .load(url)
                     .into(opponentImagePlaceHolder)
             }


             if (data.liveData.matchStatus == "Fixtures"){
                 firstCalltoAction.setBackgroundColor(
                     ContextCompat.getColor(itemView.context, R.color.red))
                 firstCalltoAction.text = "Buy Tickets"

                 secondCalltoAction.setBackgroundColor(
                     ContextCompat.getColor(itemView.context, R.color.black))
                 firstCalltoAction.text = "Hospitality"
             } else {
                 firstCalltoAction.setBackgroundColor(
                     ContextCompat.getColor(itemView.context, R.color.black))
                 firstCalltoAction.text = "Report"

                 secondCalltoAction.setBackgroundColor(
                     ContextCompat.getColor(itemView.context, R.color.white))
                 firstCalltoAction.text = "Match Centre"
             }

         }
    }
}