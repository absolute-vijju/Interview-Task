package com.developer.hyperlink.hilt

import com.developer.hyperlink.utils.Constants
import com.developer.hyperlink.api.WebService
import com.developer.hyperlink.repositories.MemesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideService(): WebService {
        var retrofit: Retrofit? = null
        if (retrofit != null)
            return retrofit.create(WebService::class.java)

        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        retrofit = Retrofit.Builder().client(okHttpClient.build())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!.create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideMemesRepository() = MemesRepository(provideService())

}