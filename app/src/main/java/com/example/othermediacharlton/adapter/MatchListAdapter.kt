package com.example.othermediacharlton.adapter

import android.icu.text.SimpleDateFormat
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

             if (data.liveData.matchStatus == "Played"){
                 matchStatusIconPlayed.setImageResource(R.drawable.icon_match_status_played)
             } else {
                 // Set the default drawable for the ImageView
                 matchStatusIconPlayed.setImageResource(R.drawable.icon_match_status_fixture)
             }
             homeTeam.text = data.matchInfo.contestant[0].name
             awayTeam.text = data.matchInfo.contestant[1].name


             if (data.liveData.matchStatus == "Played"){
                 homeTeamScore.text = data.liveData.homeScore.toString()
                 awayTeamScore.text = data.liveData.awayScore.toString()
             } else {
                 homeTeamScore.text = resources.getString(R.string.empty_string)
                 awayTeamScore.text = resources.getString(R.string.kick_off_time_default)
             }


             val url = data.matchInfo.contestant[0].crest.uri1x
             if(data.matchInfo.contestant[0].code != "CHA" &&
                 data.matchInfo.contestant[1].code != "CHA"){
                 Glide.with(opponentImagePlaceHolder)
                     .load(url)
                     .into(opponentImagePlaceHolder)
             }


             if (data.liveData.matchStatus == "Played"){
                 firstCalltoAction.setBackgroundColor(
                     ContextCompat.getColor(itemView.context, R.color.black))
                 firstCalltoAction.setText(report)
                 secondCalltoAction.setText(matchCentre)

             } else {
                 firstCalltoAction.setBackgroundResource(R.drawable.custom_button_shape)
                 firstCalltoAction.setText(buyTickets)
                 secondCalltoAction.setText(hospitality)
             }

         }

        private fun formatDate(timestampInSeconds: Long): String {
            // Convert Unix timestamp to milliseconds
            val timestampInMillis = timestampInSeconds * 1000

            // Create a Date object
            val date = Date(timestampInMillis)

            // Define the desired date format
            val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())

            // Format the date to the desired format
            return dateFormat.format(date)
        }
    }
}