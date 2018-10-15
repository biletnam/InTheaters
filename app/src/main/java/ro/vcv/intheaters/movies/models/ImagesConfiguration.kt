@file:Suppress("ArrayInDataClass")

package ro.vcv.intheaters.movies.models

import com.google.gson.annotations.SerializedName

data class ImagesConfiguration(@SerializedName("base_url")
                               var baseUrl: String,
                               @SerializedName("secure_base_url")
                               var secureBaseUrl: String,
                               @SerializedName("backdrop_sizes")
                               var backdropSizes: Array<String>,
                               @SerializedName("poster_sizes")
                               var posterSizes: Array<String>)