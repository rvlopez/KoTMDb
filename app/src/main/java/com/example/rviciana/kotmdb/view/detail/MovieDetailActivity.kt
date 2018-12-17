package com.example.rviciana.kotmdb.view.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rviciana.kotmdb.KoTMDbApplication
import com.example.rviciana.kotmdb.R
import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.extensions.hide
import com.example.rviciana.kotmdb.extensions.loadWithTransition
import com.example.rviciana.kotmdb.extensions.show
import com.example.rviciana.kotmdb.navigator.Navigator
import com.example.rviciana.kotmdb.navigator.NavigatorImpl
import com.example.rviciana.kotmdb.view.RootActivity
import com.example.rviciana.kotmdb.view.detail.di.MoviesDetailModule
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.movie_detail_layout.*
import javax.inject.Inject

class MovieDetailActivity : RootActivity(), MovieDetailContract.View {

    companion object {

        fun getCallingIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(ARG_MOVIE, movie)
            return intent
        }
        const val ARG_MOVIE = "ARG_MOVIE"

    }

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter

    private val Activity.app: KoTMDbApplication get() = application as KoTMDbApplication
    private val component by lazy { app.component.plus(MoviesDetailModule()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        supportPostponeEnterTransition()

        initToolbar()
        initRecyclerView()
        getExtras()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.arrow_left)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecyclerView() {
        with(recyclerView) {
            adapter = MovieRecommendationsAdapter(NavigatorImpl(this@MovieDetailActivity))
            layoutManager = LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun getExtras() {
        val extras = intent.extras
        extras?.apply {
            val movie = getParcelable(ARG_MOVIE) as Movie
            val imageTransitionName = movie.id.toString()
            imageView.transitionName = imageTransitionName
            imageView.loadWithTransition(movie.backdropPath, this@MovieDetailActivity)
            presenter.onViewReady(movie)
        }
    }

    override fun initializeInjector() {
        component.inject(this@MovieDetailActivity)
    }

    override fun initializePresenter() {
        presenter.setView(this@MovieDetailActivity)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_detail_movie

    override fun showMovieDetails(movie: Movie) {
        voteAvg.text = movie.voteAverage.toString()
        date.text = movie.firstAirDate
        overview.text = movie.overview
        voteNum.text = movie.voteCount.toString()
        popularity.text = movie.popularity.toString()
    }

    override fun toolbarTitleShow(movie: Movie) {
        toolbar.title = movie.name
    }

    override fun hideRecommendations() {
        recyclerView.hide()
    }

    override fun showRecommendations(movies: List<Movie>) {
        recyclerView.show()
        val moviesAdapter = recyclerView.adapter as? MovieRecommendationsAdapter
        moviesAdapter?.setMovies(movies)
    }

    override fun showLoading() = loadingLayout.show()

    override fun hideLoading() = loadingLayout.hide()
}
