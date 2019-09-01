package com.horizonlabs.kotlindemo.di.module

import com.horizonlabs.kotlindemo.view.fragment.ChatFragment
import com.horizonlabs.kotlindemo.view.fragment.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    /*  
     * We define the name of the Fragment we are going 
     * to inject the ViewModelFactory into. i.e. in our case
     * The name of the fragment: MovieListFragment
     */
    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun contributeChatFragment(): ChatFragment
}