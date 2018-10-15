package ro.vcv.intheaters.movies.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUtils {
    companion object {

        const val API_KEY = "cdb8b2b5c10378d6e0254abdbf6ff048"

        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private var retrofitInstance: Retrofit? = null
        private var apiInstance: Api? = null

        fun getApi(): Api {
            if (apiInstance == null) {
                apiInstance = getRetrofitClient().create<Api>(Api::class.java)
            }

            return apiInstance!!
        }

        private fun getRetrofitClient(): Retrofit {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofitInstance!!
        }
    }
}