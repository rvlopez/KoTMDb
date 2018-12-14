package com.example.rviciana.kotmdb.navigator

import android.app.Activity
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.view.detail.MovieDetailActivity
import com.example.rviciana.kotmdb.view.search.SearchMovieActivity

class NavigatorImpl(private val activity: Activity) : Navigator {

    override fun navigateToMovieDetail(movie: Movie, imageView: ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                imageView,
                ViewCompat.getTransitionName(imageView)!!
        )
        activity.startActivity(MovieDetailActivity.getCallingIntent(activity, movie), options.toBundle())
    }

    override fun navigateToSearchMovie() {
        activity.startActivity(SearchMovieActivity.getCallingIntent(activity))
    }

}