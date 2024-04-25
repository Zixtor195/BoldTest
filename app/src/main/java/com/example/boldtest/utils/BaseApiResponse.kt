package com.example.boldtest.utils

import android.util.Log
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        val response = apiCall()
        try {


            Log.i("Response URL", response.raw().request().url().toString())

            if (response.isSuccessful) {
                Log.i("Response", response.body().toString())
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            } /*else {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse? = gson.fromJson(response.errorBody()!!.charStream(), type)
                Log.e("Response", errorResponse.toString())
            }*/
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.i("Response Error", response.raw().request().url().toString())
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")
}