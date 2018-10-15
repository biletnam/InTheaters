package ro.vcv.intheaters.movies.helper

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import ro.vcv.intheaters.movies.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var movieImage: ImageView = itemView.findViewById(R.id.movie_item_image)

}
