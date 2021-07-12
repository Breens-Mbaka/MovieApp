package com.carolmusyoka.movieapp.util



import com.carolmusyoka.movieapp.BuildConfig
import com.carolmusyoka.movieapp.data.db.remote.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val okHttp = OkHttpClient.Builder().addInterceptor(RequestInterceptor())
    private val retrofit = Retrofit.Builder().baseUrl(ApiService.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build()).build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

    internal class RequestInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val oldRequest = chain.request()
            val url = oldRequest.url.newBuilder()
                .addQueryParameter("language", "en-US")
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()
            val newRequest = oldRequest.newBuilder().url(url).build()
            return chain.proceed(newRequest)
        }
    }
}