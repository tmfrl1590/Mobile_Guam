package com.party.guham2.di

import com.party.guham2.core.data.HttpClientFactory
import com.party.guham2.impl.DataStoreSourceImpl
import com.party.guham2.local.DataStoreSource
import com.party.guham2.presentation.screens.home.viewmodel.HomeViewModel
import com.party.guham2.presentation.screens.join.viewmodel.JoinViewModel
import com.party.guham2.presentation.screens.login.viewmodel.LoginViewModel
import com.party.guham2.presentation.screens.recruitment_detail.viewmodel.RecruitmentDetailViewModel
import com.party.guham2.presentation.screens.splash.viewmodel.SplashViewModel
import com.party.guham2.remote.BannerDataSource
import com.party.guham2.remote.PartyDataSource
import com.party.guham2.remote.RecruitmentDataSource
import com.party.guham2.remote.UserDataSource
import com.party.guham2.remote.network.banner.BannerDataSourceImpl
import com.party.guham2.remote.network.party.PartyDataSourceImpl
import com.party.guham2.remote.network.recruitment.RecruitmentDataSourceImpl
import com.party.guham2.remote.network.user.UserDataSourceImpl
import com.party.guham2.repository.BannerRepository
import com.party.guham2.repository.BannerRepositoryImpl
import com.party.guham2.repository.DataStoreRepository
import com.party.guham2.repository.DataStoreRepositoryImpl
import com.party.guham2.repository.PartyRepository
import com.party.guham2.repository.PartyRepositoryImpl
import com.party.guham2.repository.RecruitmentRepository
import com.party.guham2.repository.RecruitmentRepositoryImpl
import com.party.guham2.repository.UserRepository
import com.party.guham2.repository.UserRepositoryImpl
import com.party.guham2.usecase.banner.GetBannerListUseCase
import com.party.guham2.usecase.local.GetAccessTokenUseCase
import com.party.guham2.usecase.party.GetPartyListUseCase
import com.party.guham2.usecase.recruitment.GetRecruitmentDetailUseCase
import com.party.guham2.usecase.recruitment.GetRecruitmentListUseCase
import com.party.guham2.usecase.user.GetPositionListUseCase
import com.party.guham2.usecase.user.LoginUseCase
import com.party.guham2.usecase.user.join.CheckUserNickNameUseCase
import com.party.guham2.usecase.user.join.UserSignUpUseCase
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
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::JoinViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::RecruitmentDetailViewModel)
}

val useCaseModule = module {
    // User
    factory { LoginUseCase(get()) }
    factory { CheckUserNickNameUseCase(get()) }
    factory { UserSignUpUseCase(get()) }
    factory { GetPositionListUseCase(get()) }

    // Banner
    factory { GetBannerListUseCase(get()) }

    // Recruitment
    factory { GetRecruitmentListUseCase(get()) }
    factory { GetRecruitmentDetailUseCase(get()) }

    // Party
    factory { GetPartyListUseCase(get()) }

    // DataStore
    factory { GetAccessTokenUseCase(get()) }
}

val repositoryModule = module {
    singleOf(::UserRepositoryImpl).bind<UserRepository>()
    singleOf(::BannerRepositoryImpl).bind<BannerRepository>()
    singleOf(::RecruitmentRepositoryImpl).bind<RecruitmentRepository>()
    singleOf(::PartyRepositoryImpl).bind<PartyRepository>()
    singleOf(::DataStoreRepositoryImpl).bind<DataStoreRepository>()

}

val remoteSourceModule = module {
    singleOf(::UserDataSourceImpl).bind<UserDataSource>()
    singleOf(::BannerDataSourceImpl).bind<BannerDataSource>()
    singleOf(::RecruitmentDataSourceImpl).bind<RecruitmentDataSource>()
    singleOf(::PartyDataSourceImpl).bind<PartyDataSource>()
    singleOf(::DataStoreSourceImpl).bind<DataStoreSource>()
}