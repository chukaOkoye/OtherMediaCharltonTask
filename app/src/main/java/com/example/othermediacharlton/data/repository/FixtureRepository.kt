package com.example.othermediacharlton.data.repository

import LocalDataSource
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.example.othermediacharlton.data.api.NetworkDataSource
//import com.example.othermediacharlton.data.database.ContestantEntity
//import com.example.othermediacharlton.data.database.CrestEntity
import com.example.othermediacharlton.data.database.FixturesEntity
//import com.example.othermediacharlton.data.database.MatchInfoEntity
//import com.example.othermediacharlton.data.database.MatchLiveDataEntity
import com.example.othermediacharlton.data.database.MatchesDao
import com.example.othermediacharlton.data.database.MatchesDatabase
//import com.example.othermediacharlton.data.database.MatchesEntity
//import com.example.othermediacharlton.data.database.VenueEntity
import com.example.othermediacharlton.data.model.Contestant
import com.example.othermediacharlton.data.model.Crest
import com.example.othermediacharlton.data.model.FixturesDataModel
import com.example.othermediacharlton.data.model.Match
import com.example.othermediacharlton.data.model.MatchInfo
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.othermediacharlton.data.model.Venue
import com.google.gson.reflect.TypeToken

class FixtureRepository(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource) {

    fun getFixtures(): Observable<FixturesDataModel> {
        return localDataSource.getAllMatches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { matches ->
                if (matches.isEmpty()) {
                    networkDataSource.getMatchList()
                        .flatMap { fixturesDataModel ->
                            // Map FixturesDataModel to FixturesEntity and save to the local database
                            val fixturesEntity = toEntityFixtures(listOf(fixturesDataModel))
                            localDataSource.insertAllFixtures(fixturesEntity)
                                // Emit the original FixturesDataModel
                                .andThen(Observable.just(fixturesDataModel))
                                .doOnComplete {
                                    Log.d(TAG, "Local database added!")
                                }
                        }
                } else {
                    Observable.empty() // Emit empty observable if database is not empty
                }
            }
    }

    private fun toEntityFixtures(fixturesDataModels: List<FixturesDataModel>): List<FixturesEntity> {
        return fixturesDataModels.mapIndexed { index, fixturesDataModel ->
            val matchesJson = Gson().toJson(fixturesDataModel.match)
            FixturesEntity(index, matchesJson)
        }
    }

    private fun List<FixturesEntity>.toFixturesDataModel() = map {
        val matchString = it.matches
        val type = object : TypeToken<ArrayList<Match>>(){}.type
        val matchList: ArrayList<Match> = Gson().fromJson(matchString, type)
        FixturesDataModel(matchList)
    }
}