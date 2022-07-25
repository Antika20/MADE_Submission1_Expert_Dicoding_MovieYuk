package com.example.favorite

import android.content.Context
import com.example.movieyuk.di.FavoriteModuleDependecies
import dagger.BindsInstance
import dagger.Component


@Component(dependencies = [FavoriteModuleDependecies::class])
interface FavoriteComponent{

    fun inject(activity: Favorite)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDepencies: FavoriteModuleDependecies): Builder
        fun build(): FavoriteComponent
    }
}