package ro.vcv.intheaters.movies.mvp.contracts

import ro.vcv.intheaters.movies.models.Movie

interface MovieListContract {

    interface View {

        fun displayApiError()

        fun addMoviesToList(results: Array<Movie>?)

    }

    interface Presenter {

        fun onViewLoaded()

        fun onScrollToBottom()

    }

}