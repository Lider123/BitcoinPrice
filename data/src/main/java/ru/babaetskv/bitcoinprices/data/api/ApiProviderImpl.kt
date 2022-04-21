package ru.babaetskv.bitcoinprices.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.babaets.bitcoinprices.data.BuildConfig
import java.util.concurrent.TimeUnit

class ApiProviderImpl : ApiProvider {

    private val moshi: Moshi
        get() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private val loggingInterceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor()
            .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

    private val httpClientBuilder: OkHttpClient.Builder
        get() = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            }

    private val httpClient: OkHttpClient
        get() = httpClientBuilder.build()

    override fun provideApi(): Api =
        createRetrofit(httpClient).create(Api::class.java)

    private fun createRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

    companion object {

        private const val BASE_URL = "https://api.coindesk.com/v1/"
    }
}
