package ro.vcv.intheaters.movies.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ro.vcv.intheaters.movies.network.models.GetConfigurationResponse
import ro.vcv.intheaters.movies.network.models.GetNowPlayingResponse

interface Api {

    @GET("movie/now_playing")
    fun getNowPlaying(@Query("api_key") key: String, @Query("page") page: Int): Call<GetNowPlayingResponse>

//    @GET("search/movie")
//    fun getMovie(@Query("api_key") key: String, @Query("query") query: String): Call<GetMovieDetailsResponse>

    @GET("configuration")
    fun getConfiguration(@Query("api_key") key: String): Call<GetConfigurationResponse>
}