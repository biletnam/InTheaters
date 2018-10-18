package ro.vcv.intheaters.movies.helper

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ro.vcv.intheaters.movies.R
import ro.vcv.intheaters.movies.models.Movie

class MoviesListAdapter(private val moviesList: MutableList<Movie>,
                        private val clickListener: (Movie) -> Unit)
    : RecyclerView.Adapter<MovieViewHolder>() {

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
            val circularProgressDrawable = CircularProgressDrawable(context!!)
            circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE)
            circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(context!!, android.R.color.white))
            circularProgressDrawable.start()

            Glide.with(context!!)
                    .load(moviesList[position].posterPath)
                    .apply(RequestOptions()
                            .placeholder(circularProgressDrawable))
                    .into(holder.movieImage)

            holder.bind(moviesList[position], clickListener)
        }
    }

    fun clearData() {
        moviesList.clear()
        notifyDataSetChanged()
    }

    fun addData(movies: List<Movie>) {
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }
}
