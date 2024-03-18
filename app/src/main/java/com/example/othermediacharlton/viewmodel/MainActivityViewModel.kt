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


class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FixtureRepository
    private val _matchList: MutableLiveData<FixturesDataModel> = MutableLiveData()
    val matchList: LiveData<FixturesDataModel> = _matchList

    init {
        val apiService = ApiService.getInstance()
        val database = MatchesDatabase.getInstance(application)
        val localDataSource = LocalDataSource(database)
        val networkDataSource = NetworkDataSource(apiService)
        repository = FixtureRepository(networkDataSource, localDataSource)
    }

    fun getMatchListLiveData(): LiveData<FixturesDataModel> {
        val values = repository.getFixtures()

        values.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { matches ->
                    // Update the LiveData with the fetched match list
                    _matchList.value = matches
                    Log.d(TAG, "ViewModel Connection success!")
                },
                { throwable ->
                    // Handle errors if any
                    throwable.message
                    Log.e(TAG, "Error fetching match list: ${throwable.message}")
                }
            ).isDisposed
        return _matchList
    }
}
