package ro.vcv.intheaters.movies.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ro.vcv.intheaters.movies.network.models.GetConfigurationResponse
import ro.vcv.intheaters.movies.network.models.GetMoviesResponse

interface Api {

    @GET("movie/now_playing")
    fun getNowPlaying(@Query("api_key") key: String, @Query("page") page: Int): Call<GetMoviesResponse>

    @GET("search/movie")
    fun getMovies(@Query("api_key") key: String, @Query("query") query: String,  @Query("page") page: Int): Call<GetMoviesResponse>

    @GET("configuration")
    fun getConfiguration(@Query("api_key") key: String): Call<GetConfigurationResponse>
}