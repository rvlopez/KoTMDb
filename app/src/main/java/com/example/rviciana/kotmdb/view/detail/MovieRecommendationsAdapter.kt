package com.example.rviciana.kotmdb.view.detail

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rviciana.kotmdb.R
import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.extensions.inflate
import com.example.rviciana.kotmdb.extensions.load
import com.example.rviciana.kotmdb.navigator.Navigator
import kotlinx.android.synthetic.main.activity_detail_recommendations_item.view.*

class MovieRecommendationsAdapter(private val navigator: Navigator) :
    RecyclerView.Adapter<MovieRecommendationsViewHolder>() {

    private var movies: List<Movie> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecommendationsViewHolder =
        MovieRecommendationsViewHolder(parent.inflate(R.layout.activity_detail_recommendations_item))

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieRecommendationsViewHolder, position: Int) {
        holder.bind(movies[position], navigator)
    }
}

class MovieRecommendationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie, navigator: Navigator) = with(itemView) {
        ViewCompat.setTransitionName(moviePoster, movie.id.toString())
        moviePoster.load(movie.posterPath)
        rateAvg.text = movie.voteAverage.toString()
        setOnClickListener { navigator.navigateToMovieDetail(movie, moviePoster) }
    }
}