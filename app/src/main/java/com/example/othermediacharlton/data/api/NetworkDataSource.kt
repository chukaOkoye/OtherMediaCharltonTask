package com.example.othermediacharlton.data.api

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import com.example.othermediacharlton.data.model.FixturesDataModel
import com.example.othermediacharlton.data.model.Match
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import io.reactivex.internal.operators.flowable.BlockingFlowableNext
import io.reactivex.rxkotlin.subscribeBy


class NetworkDataSource(private val apiService: ApiService) {

    fun getMatchList(): Observable<FixturesDataModel> {
        return apiService.getFixtures()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error ->
                // Handle or log the error
                Log.e(ContentValues.TAG, "Error fetching apiservice: ${error.message}")
            }
    }

}