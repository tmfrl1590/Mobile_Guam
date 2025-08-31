package com.party.guham2.model.user.join

import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class UserSignUpEntity(
    val accessToken: String,
): DataMapper<UserSignUp>{
    override fun toDomain(): UserSignUp {
        return UserSignUp(
            accessToken = accessToken
        )
    }
}
