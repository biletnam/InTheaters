package ro.vcv.intheaters.movies.mvp.presenters

import ro.vcv.intheaters.movies.models.Movie
import ro.vcv.intheaters.movies.mvp.contracts.MovieListContract
import ro.vcv.intheaters.movies.network.interactors.GetConfiguration
import ro.vcv.intheaters.movies.network.interactors.GetNowPlaying
import ro.vcv.intheaters.movies.network.models.GetConfigurationResponse
import ro.vcv.intheaters.movies.network.models.GetNowPlayingResponse

class MovieListPresenter(private val view: MovieListContract.View) : MovieListContract.Presenter {

    private var baseUrl: String? = null
    private var backdropSize: String? = null
    private var posterSize: String? = null

    private var currentPage: Int? = 1
    private var totalPages: Int? = null

    override fun onViewLoaded() {
        GetConfiguration.execute(object: GetConfiguration.GetMovieDetailsCallback {
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

                getNowPlaying(1)
            }

            override fun onError() {
                view.displayApiError()
            }
        })
    }

    private fun getNowPlaying(page: Int) {
        GetNowPlaying.execute(object : GetNowPlaying.GetNowPlayingCallback {
            override fun onSuccess(responseBody: GetNowPlayingResponse?) {
                currentPage = responseBody?.page
                totalPages = responseBody?.totalPages

                view.displayNowPlaying(getFullPathMovies(responseBody?.results))
            }

            override fun onError() {
                view.displayApiError()
            }
        }, page)
    }

    private fun getFullPathMovies(movies: Array<Movie>?): Array<Movie>? {
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