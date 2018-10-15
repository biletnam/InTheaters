package ro.vcv.intheaters.movies.network.models

import com.google.gson.annotations.SerializedName
import ro.vcv.intheaters.movies.models.Movie
import java.util.*

data class GetNowPlayingResponse(var results: Array<Movie>,
                                 var page: Int,
                                 @SerializedName("total_pages")
                                 var totalPages: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetNowPlayingResponse

        if (!Arrays.equals(results, other.results)) return false
        if (page != other.page) return false
        if (totalPages != other.totalPages) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(results)
        result = 31 * result + page
        result = 31 * result + totalPages
        return result
    }
}