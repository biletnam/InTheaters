package ro.vcv.intheaters.movies.models

import com.google.gson.annotations.SerializedName

data class Movie(@SerializedName("original_title")
                 var originalTitle: String,
                 var overview: String,
                 @SerializedName("poster_path")
                 var posterPath: String,
                 @SerializedName("backdrop_path")
                 var backdropPath: String)