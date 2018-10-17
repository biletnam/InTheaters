package ro.vcv.intheaters.movies.mvp.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import ro.vcv.intheaters.movies.R
import ro.vcv.intheaters.movies.helper.MoviesListAdapter
import ro.vcv.intheaters.movies.models.Movie
import ro.vcv.intheaters.movies.mvp.contracts.MovieListContract
import ro.vcv.intheaters.movies.mvp.presenters.MovieListPresenter
import ro.vcv.intheaters.movies.mvp.views.MovieDetails.Companion.INTENT_EXTRA_MOVIE

class MovieList : AppCompatActivity(), MovieListContract.View {

    private val presenter: MovieListContract.Presenter = MovieListPresenter(this)

    private var toolbar: Toolbar? = null
    private var moviesRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_list)

        setSupportActionBar(findViewById(R.id.movies_list_toolbar))

        initToolbar()
        initMoviesList()

        presenter.onViewLoaded()
    }

    override fun displayApiError() {
        Toast.makeText(this, "An error occurred while getting data from the server ;(", Toast.LENGTH_LONG).show()
    }

    override fun displayNowPlaying(results: Array<Movie>?) {
        if (results != null) {
            moviesRecyclerView!!.adapter = MoviesListAdapter(results.toList()) {
                movie : Movie -> movieClicked(movie)
            }
        }
    }

    private fun initMoviesList() {
        moviesRecyclerView = findViewById(R.id.movies_list_recycler_view)
        moviesRecyclerView!!.setHasFixedSize(true)
        moviesRecyclerView!!.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.movies_list_toolbar)
        setSupportActionBar(toolbar)
    }

    private fun movieClicked(movie: Movie) {
        val intent = Intent(this, MovieDetails::class.java)
        intent.putExtra(INTENT_EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}
