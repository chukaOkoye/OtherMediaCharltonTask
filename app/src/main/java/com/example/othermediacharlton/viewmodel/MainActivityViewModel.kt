package com.example.othermediacharlton.viewmodel

import LocalDataSource
import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.othermediacharlton.data.api.ApiService
import com.example.othermediacharlton.data.api.NetworkDataSource
import com.example.othermediacharlton.data.database.MatchesDatabase
import com.example.othermediacharlton.data.model.FixturesDataModel
import com.example.othermediacharlton.data.model.Match
import com.example.othermediacharlton.data.repository.FixtureRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("CheckResult")
class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val matchList: MutableLiveData<FixturesDataModel> = MutableLiveData()

    init {
        val apiService = ApiService.getInstance()
        val database = MatchesDatabase.getInstance(application)
        val localDataSource = LocalDataSource(database)
        val networkDataSource = NetworkDataSource(apiService)
        val repository = FixtureRepository(networkDataSource, localDataSource)

        repository.getFixtures()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { matches ->
                    // Update the LiveData with the fetched match list
                    matchList.value = matches
                    Log.d(TAG, "ViewModel Connection success!")
                },
                { throwable ->
                    // Handle errors if any
                    Log.e(TAG, "Error fetching match list: ${throwable.message}")
                }
            )
    }

    fun getMatchListLiveData(): LiveData<FixturesDataModel> {
        return matchList
    }
}



//    fun getMatchListObserver(): MutableLiveData<List<Match>>{
//        return matchList
//    }


//    fun getMatchListObserverRx(): Observer<List<Match>>{
//        return object : Observer<List<Match>>{
//            override fun onComplete() {
//
//            }
//
//            override fun onError(e: Throwable) {
//                matchList.postValue(null)
//            }
//
//            override fun onNext(t: List<Match>) {
//                matchList.postValue(t)
//            }
//
//            override fun onSubscribe(d: Disposable) {
//
//            }
//        }
//    }

