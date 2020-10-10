package kz.kamadi.androidtest.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import kz.kamadi.androidtest.data.network.UserApi
import kz.kamadi.androidtest.data.repository.UserRepositoryImpl
import kz.kamadi.androidtest.di.viewmodel.ContextProvider
import kz.kamadi.androidtest.di.viewmodel.ContextProviderImpl
import kz.kamadi.androidtest.domain.repository.UserRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object DataModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideApi(client: OkHttpClient): UserApi {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }

    @Provides
    fun provideContext(contextProviderImpl: ContextProviderImpl): ContextProvider {
        return contextProviderImpl
    }
}