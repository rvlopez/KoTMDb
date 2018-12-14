package com.example.rviciana.kotmdb.view.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.view.detail.MovieDetailActivity

class SearchMovieActivity : AppCompatActivity() {

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, SearchMovieActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}