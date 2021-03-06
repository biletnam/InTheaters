package ro.vcv.intheaters.movies.network.interactors

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ro.vcv.intheaters.movies.network.ApiUtils
import ro.vcv.intheaters.movies.network.models.GetMoviesResponse

class GetNowPlaying {

    companion object {
        fun execute(callback: GetNowPlayingCallback, page: Int) {
            ApiUtils.getApi().getNowPlaying(ApiUtils.API_KEY, page).enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            callback.onSuccess(response.body())
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    callback.onError()
                }
            })
        }
    }

    interface GetNowPlayingCallback {
        fun onSuccess(responseBody: GetMoviesResponse?)

        fun onError()
    }

}