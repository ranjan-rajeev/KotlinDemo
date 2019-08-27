package com.horizonlabs.kotlindemo.di.module

import com.horizonlabs.kotlindemo.view.activity.ChatActivity
import com.horizonlabs.kotlindemo.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity


    @ContributesAndroidInjector
    abstract fun contributeChatActivity(): ChatActivity
}