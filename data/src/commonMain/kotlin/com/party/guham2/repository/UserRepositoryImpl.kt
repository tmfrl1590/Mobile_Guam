package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.core.domain.map
import com.party.guham2.model.user.Position
import com.party.guham2.remote.UserDataSource

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
): UserRepository{
    override suspend fun getPositionList(main: String): Result<List<Position>, DataError> {
        return userDataSource.getPositionList(main = main).map { it.map { it.toDomain() } }
    }

}