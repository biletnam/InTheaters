package ro.vcv.intheaters.movies.mvp.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ro.vcv.intheaters.movies.R
import ro.vcv.intheaters.movies.models.Movie

class MovieDetails : AppCompatActivity() {

    private var image: ImageView? = null
    private var title: TextView? = null
    private var description: TextView? = null

    companion object {
        const val INTENT_EXTRA_MOVIE: String = "INTENT_EXTRA_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)

        initView()
        populateView(intent.getSerializableExtra(INTENT_EXTRA_MOVIE) as Movie)
    }

    private fun initView() {
        image = findViewById(R.id.movie_details_image)
        title = findViewById(R.id.movie_details_title)
        description = findViewById(R.id.movie_details_description)
    }

    private fun populateView(movie: Movie) {
        Glide.with(this)
                .load(movie.backdropPath)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_movie_white_64dp))
                .into(image!!)

        title!!.text = movie.originalTitle
        description!!.text = movie.overview
    }
}