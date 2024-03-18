package com.example.othermediacharlton.adapter

import android.content.ContentValues.TAG
import android.content.res.ColorStateList
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.util.Log
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
import java.util.Date
import java.util.Locale

class MatchListAdapter: RecyclerView.Adapter<MatchListAdapter.MyViewHolder>()   {

    var matchListData = ArrayList<Match>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return matchListData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(matchListData[position])
    }

    fun setData(fixturesDataModel: FixturesDataModel) {
        matchListData.clear()
        matchListData.addAll(fixturesDataModel.match)
        notifyDataSetChanged()
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val matchCentre = R.string.match_centre
        val hospitality = R.string.hospitality
        val buyTickets = R.string.buy_tickets
        val report = R.string.report

        private val resources = itemView.context.resources

        private val matchInfo = view.findViewById<TextView>(R.id.matchInfoDate)
        private val venueShortName = view.findViewById<TextView>(R.id.venueShortName)
        private val matchStatusIconPlayed = view.findViewById<ImageView>(R.id.matchStatusIconPlayed)
        private val homeTeam = view.findViewById<TextView>(R.id.homeTeam)
        private val homeTeamScore = view.findViewById<TextView>(R.id.homeTeamScore)
        private val awayTeam = view.findViewById<TextView>(R.id.awayTeam)
        private val awayTeamScore = view.findViewById<TextView>(R.id.awayTeamScore)
        private val opponentImagePlaceHolder = view.findViewById<ImageView>(R.id.opponentImagePlaceHolder)
        private val firstCalltoAction = view.findViewById<Button>(R.id.firstCalltoAction)
        private val secondCalltoAction = view.findViewById<Button>(R.id.secondCalltoAction)


         fun bind(data: Match){

             val timestampInSeconds = formatDate(data.matchInfo.date.toLong())
             matchInfo.text = timestampInSeconds
             venueShortName.text = data.matchInfo.venue.shortName
             firstCalltoAction.setOnClickListener {
                 Log.d(TAG, "Clicked firstCalltoAction: ${data.matchInfo.firstCallToAction}")
             }

             secondCalltoAction.setOnClickListener {
                 Log.d(TAG, "Clicked secondCalltoAction: ${data.matchInfo.firstCallToAction}")
             }

             if (data.liveData.matchStatus == "Played") {
                 matchStatusIconPlayed.setImageResource(R.drawable.icon_match_status_played)
                 homeTeam.text = data.matchInfo.contestant[0].name
                 awayTeam.text = data.matchInfo.contestant[1].name
                 homeTeamScore.text = data.liveData.homeScore.toString()
                 awayTeamScore.text = data.liveData.awayScore.toString()
                 firstCalltoAction.setText(report)
                 secondCalltoAction.setText(matchCentre)
                 firstCalltoAction.backgroundTintList =
                     ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.black))
                 secondCalltoAction.backgroundTintList =
                     ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.clear))
                 secondCalltoAction.setTextColor(
                     ContextCompat.getColor(itemView.context, R.color.red))
             } else {
                 matchStatusIconPlayed.setImageResource(R.drawable.icon_match_status_fixture)
                 homeTeam.text = data.matchInfo.contestant[0].name
                 awayTeam.text = data.matchInfo.contestant[1].name
                 homeTeamScore.text = resources.getString(R.string.empty_string)
                 awayTeamScore.text = resources.getString(R.string.kick_off_time_default)
                 firstCalltoAction.setText(buyTickets)
                 firstCalltoAction.backgroundTintList =
                     ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.red))
                 firstCalltoAction.setText(buyTickets)
                 secondCalltoAction.setText(hospitality)
                 secondCalltoAction.backgroundTintList =
                     ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.black))
                 secondCalltoAction.setTextColor(Color.WHITE)
             }

             var url: String? = null
             for (contestant in data.matchInfo.contestant) {
                 if (contestant.code != "CHA") {
                     url = contestant.crest.uri1x
                     break
                 }
             }
             url?.let {
                 Glide.with(itemView)
                     .load(url)
                     .into(opponentImagePlaceHolder)
             }

         }

        private fun formatDate(timestampInSeconds: Long): String {
            val timestampInMillis = timestampInSeconds * 1000

            val date = Date(timestampInMillis)

            val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())

            return dateFormat.format(date)
        }
    }
}