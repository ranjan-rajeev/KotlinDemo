package com.horizonlabs.kotlindemo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.horizonlabs.kotlindemo.di.ViewModelKey
import com.horizonlabs.kotlindemo.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  MovieListViewModel.class as key,
     * and a Provider that will build a MovieListViewModel
     * object.
     * 
     * */

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    protected abstract fun movieListViewModel(userViewModel: UserViewModel): ViewModel
}