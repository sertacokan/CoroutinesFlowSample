package com.example.coroutineflowsample.di

import androidx.room.Room
import com.example.coroutineflowsample.database.UserDatabase
import com.example.coroutineflowsample.network.UserService
import com.example.coroutineflowsample.repositories.UserRepository
import com.example.coroutineflowsample.utils.MIGRATION_1_2
import com.example.coroutineflowsample.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(UserService::class.java)
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), UserDatabase::class.java, "UserDatabase")
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    factory {
        get<UserDatabase>().userDao
    }
}

val repositoryModule = module {
    single {
        UserRepository(get(), get())
    }
}