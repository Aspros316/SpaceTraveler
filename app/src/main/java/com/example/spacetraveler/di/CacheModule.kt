package com.example.spacetraveler.di

import androidx.room.Room
import com.example.spacetraveler.data.cache.TravelCacheImpl
import com.example.spacetraveler.data.cache.database.TravelDatabase
import com.example.spacetraveler.data.cache.database.config.TRAVEL_DB
import com.example.spacetraveler.data.source.TravelCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheModule = module {

    single<TravelCache> { TravelCacheImpl(travelDao = get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            TravelDatabase::class.java,
            TRAVEL_DB
        ).build()
    }

    single { get<TravelDatabase>().travelDao() }


}