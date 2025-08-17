package com.party.guham2.remote

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.PositionEntity

interface UserDataSource {

    suspend fun getPositionList(main: String): Result<List<PositionEntity>, DataError>
}