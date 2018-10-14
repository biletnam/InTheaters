package ro.vcv.intheaters.movies.mvp.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import ro.vcv.intheaters.movies.R
import ro.vcv.intheaters.movies.helper.MoviesListAdapter
import ro.vcv.intheaters.movies.models.Movie
import ro.vcv.intheaters.movies.mvp.contracts.MovieListContract

class MovieList : AppCompatActivity(), MovieListContract.View {

    private var toolbar: Toolbar? = null
    private var moviesRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_list)

        setSupportActionBar(findViewById(R.id.movies_list_toolbar))

        initToolbar()
        initMoviesList()
    }

    private fun initMoviesList() {
        moviesRecyclerView = findViewById(R.id.movies_list_recycler_view)
        moviesRecyclerView!!.setHasFixedSize(true)
        moviesRecyclerView!!.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        val movies = ArrayList<Movie>()
        movies.add(Movie())
        movies.add(Movie())
        movies.add(Movie())

        moviesRecyclerView!!.adapter = MoviesListAdapter(movies)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.movies_list_toolbar)
        setSupportActionBar(toolbar)
    }
}
