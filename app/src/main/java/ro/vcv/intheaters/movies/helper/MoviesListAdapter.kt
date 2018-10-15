package ro.vcv.intheaters.movies.helper

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ro.vcv.intheaters.movies.R
import ro.vcv.intheaters.movies.models.Movie

class MoviesListAdapter(private val moviesList: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context

        val layoutView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (position < moviesList.size) {
            Glide.with(context!!)
                    .load(moviesList[position].posterPath)
                    .into(holder.movieImage)
        }
    }

}
