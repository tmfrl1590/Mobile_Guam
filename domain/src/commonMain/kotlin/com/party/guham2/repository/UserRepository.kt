package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.Position

interface UserRepository {

    suspend fun getPositionList(main: String): Result<List<Position>, DataError>
}