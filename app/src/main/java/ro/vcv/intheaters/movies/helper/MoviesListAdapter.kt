package ro.vcv.intheaters.movies.helper

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ro.vcv.intheaters.movies.R
import ro.vcv.intheaters.movies.models.Movie

class MoviesListAdapter(private val moviesList: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (position < moviesList.size) {
            val movie = moviesList[position]
//            holder.productTitle.text = movie.title
//            holder.productPrice.text = movie.price
//            ImageRequester.setImageFromUrl(holder.productImage, movie.url)
        }
    }

}
