package com.example.rviciana.kotmdb.view.list

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rviciana.kotmdb.KoTMDbApplication
import com.example.rviciana.kotmdb.R
import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.extensions.hide
import com.example.rviciana.kotmdb.extensions.show
import com.example.rviciana.kotmdb.navigator.NavigatorImpl
import com.example.rviciana.kotmdb.view.RootActivity
import com.example.rviciana.kotmdb.view.list.di.MoviesModule
import kotlinx.android.synthetic.main.activity_list_movie.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import javax.inject.Inject

class MovieListActivity : RootActivity(), MovieListContract.View {

    @Inject
    lateinit var presenter: MovieListContract.Presenter

    private val Activity.app: KoTMDbApplication get() = application as KoTMDbApplication
    private val component by lazy { app.component.plus(MoviesModule()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        presenter.onViewReady()
    }

    private fun initViews() {
        with(recyclerView) {
            adapter = MoviesAdapter(NavigatorImpl(this@MovieListActivity))
            val linearLayoutManager = LinearLayoutManager(this@MovieListActivity)
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.itemCount - 1)
                        presenter.onBottomReached()
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
            setHasFixedSize(true)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun initializeInjector() {
        component.inject(this@MovieListActivity)
    }

    override fun initializePresenter() {
        presenter.setView(this@MovieListActivity)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_list_movie

    override fun showMovies(movieList: List<Movie>) {
        recyclerView.show()
        errorLayout.hide()

        val moviesAdapter = recyclerView.adapter as MoviesAdapter
        moviesAdapter.setMovies(movieList)
    }

    override fun addMovies(movieList: List<Movie>) {
        val moviesAdapter = recyclerView.adapter as MoviesAdapter
        moviesAdapter.addMovies(movieList)
    }

    override fun showLoading() = loadingLayout.show()

    override fun hideLoading() = loadingLayout.hide()

    override fun showError(throwable: Throwable) {
        recyclerView.hide()
        errorLayout.show()
        errorMessage.text = throwable.message
    }
}
