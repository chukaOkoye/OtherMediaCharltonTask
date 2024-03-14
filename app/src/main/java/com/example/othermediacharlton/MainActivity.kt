package com.example.othermediacharlton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.othermediacharlton.adapter.MatchListAdapter
import com.example.othermediacharlton.data.model.Match
import com.example.othermediacharlton.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var matchListAdapter: MatchListAdapter
    private lateinit var recyclerView: RecyclerView

//    private val recyclerView = RecyclerView(baseContext)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        initRecyclerView()
        loadAPIData()
    }

    fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        matchListAdapter = MatchListAdapter()
        recyclerView.adapter = matchListAdapter

    }

    fun loadAPIData(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getMatchListLiveData().observe(this) { fixturesDataModel ->
            matchListAdapter.setData(fixturesDataModel)
        }
    }
}