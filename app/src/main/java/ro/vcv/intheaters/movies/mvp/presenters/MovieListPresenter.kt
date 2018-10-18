package ro.vcv.intheaters.movies.mvp.presenters

import ro.vcv.intheaters.movies.models.Movie
import ro.vcv.intheaters.movies.mvp.contracts.MovieListContract
import ro.vcv.intheaters.movies.network.interactors.GetConfiguration
import ro.vcv.intheaters.movies.network.interactors.GetMovies
import ro.vcv.intheaters.movies.network.interactors.GetNowPlaying
import ro.vcv.intheaters.movies.network.models.GetConfigurationResponse
import ro.vcv.intheaters.movies.network.models.GetMoviesResponse

class MovieListPresenter(private val view: MovieListContract.View) : MovieListContract.Presenter {

    private var baseUrl: String? = null
    private var backdropSize: String? = null
    private var posterSize: String? = null

    private var currentPage: Int? = 1
    private var totalPages: Int? = null

    private var lastSearched: String? = null
    private var isSearchActive: Boolean = false

    override fun onViewLoaded() {
        GetConfiguration.execute(object : GetConfiguration.GetMovieDetailsCallback {
            override fun onSuccess(responseBody: GetConfigurationResponse?) {
                if (baseUrl == null) {
                    baseUrl = responseBody!!.images.baseUrl
                }

                if (backdropSize == null) {
                    backdropSize = responseBody!!.images.backdropSizes[1]
                }

                if (posterSize == null) {
                    posterSize = responseBody!!.images.posterSizes[1]
                }

                getNowPlaying(currentPage!!)
            }

            override fun onError() {
                view.displayApiError()
            }
        })
    }

    override fun onScrollToBottom() {
        if (totalPages != null && currentPage!! >= totalPages!!) {
            return
        }

        if (isSearchActive) {
            onSearch(lastSearched!!, currentPage!! + 1)
        } else {
            getNowPlaying(currentPage!! + 1)
        }
    }

    override fun onSearch(query: String) {
        onSearch(query, 1)
    }

    override fun onSearch(query: String, page: Int) {
        isSearchActive = true

        GetMovies.execute(object : GetMovies.GetMoviesCallback {
            override fun onSuccess(responseBody: GetMoviesResponse?) {
                totalPages = responseBody?.totalPages
                currentPage = responseBody?.page
                lastSearched = query

                view.addMoviesToList(adjustMoviesPath(responseBody?.results), currentPage == 1)
            }

            override fun onError() {
                view.displayApiError()
            }
        }, query, page)
    }

    override fun onSearchClosed() {
        isSearchActive = false
        currentPage = 1
        getNowPlaying(currentPage!!)
    }

    private fun getNowPlaying(page: Int) {
        GetNowPlaying.execute(object : GetNowPlaying.GetNowPlayingCallback {
            override fun onSuccess(responseBody: GetMoviesResponse?) {
                totalPages = responseBody?.totalPages
                currentPage = responseBody?.page

                view.addMoviesToList(adjustMoviesPath(responseBody?.results), currentPage == 1)
            }

            override fun onError() {
                view.displayApiError()
            }
        }, page)
    }

    private fun adjustMoviesPath(movies: Array<Movie>?): Array<Movie>? {
        if (movies == null) {
            return null
        }

        for (movie: Movie in movies) {
            movie.posterPath = "$baseUrl$posterSize" + movie.posterPath
            movie.backdropPath = "$baseUrl$backdropSize" + movie.backdropPath
        }

        return movies
    }
}