package ro.vcv.intheaters.movies.mvp.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.Toast
import ro.vcv.intheaters.movies.R
import ro.vcv.intheaters.movies.helper.InfiniteScrollListener
import ro.vcv.intheaters.movies.helper.MoviesListAdapter
import ro.vcv.intheaters.movies.models.Movie
import ro.vcv.intheaters.movies.mvp.contracts.MovieListContract
import ro.vcv.intheaters.movies.mvp.presenters.MovieListPresenter
import ro.vcv.intheaters.movies.mvp.views.MovieDetails.Companion.INTENT_EXTRA_MOVIE

class MovieList : AppCompatActivity(), MovieListContract.View {

    private val presenter: MovieListContract.Presenter = MovieListPresenter(this)

    private var toolbar: Toolbar? = null
    private var moviesRecyclerView: RecyclerView? = null
    private var moviesNestedScrollView: NestedScrollView? = null

    private var adapter = MoviesListAdapter(mutableListOf()) { movie: Movie ->
        movieClicked(movie)
    }

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

    override fun addMoviesToList(results: Array<Movie>?, shouldClear: Boolean) {
        if (results == null) {
            return
        }

        if (shouldClear) {
            adapter.clearData()
            moviesNestedScrollView!!.fullScroll(View.FOCUS_UP)
        }

        adapter.addData(results.toMutableList())
    }

    private fun initMoviesList() {
        val layout = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        moviesRecyclerView = findViewById(R.id.movies_list_recycler_view)
        moviesRecyclerView!!.setHasFixedSize(true)
        moviesRecyclerView!!.layoutManager = layout
        moviesRecyclerView!!.adapter = adapter

        moviesNestedScrollView = findViewById(R.id.movies_list_nested_scroll_view)
        moviesNestedScrollView!!.setOnScrollChangeListener(InfiniteScrollListener({ presenter.onScrollToBottom() }, layout))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu!!.findItem(R.id.app_bar_search)

        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.onSearch(query)

                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                searchItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })

        searchView.setOnCloseListener(object : android.widget.SearchView.OnCloseListener, SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                presenter.onSearchClosed()
                return false
            }

        })

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
