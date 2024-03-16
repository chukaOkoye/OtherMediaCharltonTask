package com.example.othermediacharlton.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MatchesDao {

    @Query("SELECT * FROM fixtures")
    fun getAllMatches(): Observable<List<FixturesEntity>>

    @Query("SELECT * FROM fixtures WHERE id = :matchId")
    fun getMatchById(matchId: Int): FixturesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(matches: List<FixturesEntity>): Completable

    @Delete
    fun deleteMatch(match: FixturesEntity): Completable

}