package com.party.guham2.di

import com.party.guham2.core.data.HttpClientFactory
import com.party.guham2.presentation.screens.home.viewmodel.HomeViewModel
import com.party.guham2.remote.BannerRemoteSource
import com.party.guham2.remote.network.banner.BannerRemoteSourceImpl
import com.party.guham2.repository.BannerRepository
import com.party.guham2.repository.BannerRepositoryImpl
import com.party.guham2.usecase.banner.GetBannerListUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

val useCaseModule = module {
    // Banner
    factory { GetBannerListUseCase(get()) }
}

val repositoryModule = module {
    singleOf(::BannerRepositoryImpl).bind<BannerRepository>()
}

val remoteSourceModule = module {
    singleOf(::BannerRemoteSourceImpl).bind<BannerRemoteSource>()
}