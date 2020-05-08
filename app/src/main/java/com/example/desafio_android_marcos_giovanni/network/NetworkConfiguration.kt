package com.example.desafio_android_marcos_giovanni.network

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkConfiguration {
    @Provides
    @Singleton
    fun providesNetworkService() : NetworkService {
        return NetworkService()
    }
}