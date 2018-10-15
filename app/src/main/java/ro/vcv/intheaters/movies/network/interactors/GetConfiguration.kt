package ro.vcv.intheaters.movies.network.interactors

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ro.vcv.intheaters.movies.network.ApiUtils
import ro.vcv.intheaters.movies.network.models.GetConfigurationResponse

class GetConfiguration {

    companion object {
        fun execute(callback: GetMovieDetailsCallback) {
            ApiUtils.getApi().getConfiguration(ApiUtils.API_KEY).enqueue(object : Callback<GetConfigurationResponse> {
                override fun onResponse(call: Call<GetConfigurationResponse>, response: Response<GetConfigurationResponse>) {
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

                override fun onFailure(call: Call<GetConfigurationResponse>, t: Throwable) {
                    callback.onError()
                }
            })
        }
    }

    interface GetMovieDetailsCallback {
        fun onSuccess(responseBody: GetConfigurationResponse?)

        fun onError()
    }

}