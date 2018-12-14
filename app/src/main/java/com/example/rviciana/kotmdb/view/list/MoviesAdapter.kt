package com.example.rviciana.kotmdb.view.list

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rviciana.kotmdb.R
import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.extensions.inflate
import com.example.rviciana.kotmdb.extensions.load
import com.example.rviciana.kotmdb.navigator.Navigator
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(private val navigator: Navigator) : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies: MutableList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
            MoviesViewHolder(parent.inflate(R.layout.item_movie))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
            holder.bind(movies[position], navigator)

    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun addMovies(movies: List<Movie>) {
        val sizeBefore = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeInserted(sizeBefore, this.movies.size)
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie, navigator: Navigator) = with(itemView) {
        ViewCompat.setTransitionName(backgroundImage, movie.id.toString())
        backgroundImage.load(movie.backdropPath)
        title.text = movie.name
        rating.text = movie.voteAverage.toString()
        setOnClickListener { navigator.navigateToMovieDetail(movie, backgroundImage, title) }
    }
}