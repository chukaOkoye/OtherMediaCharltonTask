package com.example.othermediacharlton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.othermediacharlton.adapter.MatchListAdapter
import com.example.othermediacharlton.data.model.Match
import com.example.othermediacharlton.viewmodel.MainActivityViewModel
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var matchListAdapter: MatchListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        emptyStateView = findViewById(R.id.emptyStateLayout)

        loadAPIData()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        matchListAdapter = MatchListAdapter()
        recyclerView.adapter = matchListAdapter

    }

    private fun loadAPIData() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getMatchListLiveData()

        viewModel.matchList.observe(this) { fixturesDataModel ->
            if (fixturesDataModel.match.isEmpty()) {
                // Data is not present, show empty state
                showEmptyState()

            } else {
                // Data is present, show RecyclerView
                showRecyclerView()
                matchListAdapter.setData(fixturesDataModel)
            }
        }
    }
    private fun showEmptyState() {
        recyclerView.visibility = View.GONE
        emptyStateView.visibility = View.VISIBLE
    }

    private fun showRecyclerView() {
        recyclerView.visibility = View.VISIBLE
        emptyStateView.visibility = View.GONE
    }
}
