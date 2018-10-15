package ro.vcv.intheaters.movies.mvp.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ro.vcv.intheaters.movies.R

class MovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)
    }
}