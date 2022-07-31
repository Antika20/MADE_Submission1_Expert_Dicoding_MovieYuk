package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.MovieDao
import com.example.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object DatabaseModule {

    val passphrase: ByteArray = SQLiteDatabase.getBytes("example".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =


        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,"Movie.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .openHelperFactory(factory)
            .build()

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()


}