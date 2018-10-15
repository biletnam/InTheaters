package ro.vcv.intheaters.movies.mvp.contracts

import ro.vcv.intheaters.movies.models.Movie

interface MovieListContract {

    interface View {

        fun displayApiError()

        fun displayNowPlaying(results: Array<Movie>?)

    }

    interface Presenter {
        fun onViewLoaded()
    }

}