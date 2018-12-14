package com.example.rviciana.kotmdb.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.example.rviciana.kotmdb.R
import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.extensions.loadWithTransition
import com.example.rviciana.kotmdb.view.RootActivity
import kotlinx.android.synthetic.main.activity_detail_movie.*

class MovieDetailActivity : RootActivity() {

    companion object {

        fun getCallingIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(ARG_MOVIE, movie)
            return intent
        }
        const val ARG_MOVIE = "ARG_MOVIE"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        supportPostponeEnterTransition()

        getExtras()
    }

    private fun getExtras() {
        val extras = intent.extras
        extras?.apply {
            val movie = getParcelable(ARG_MOVIE) as Movie
            val imageTransitionName = movie.id.toString()
            imageView.transitionName = imageTransitionName
            imageView.loadWithTransition(movie.backdropPath, this@MovieDetailActivity)
            //presenter.onViewReady(movie)
        }
    }

    override fun initializeInjector() {

    }

    override fun initializePresenter() {

    }

    override fun getLayoutResourceId(): Int = R.layout.activity_detail_movie
}
