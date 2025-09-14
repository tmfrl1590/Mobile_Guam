package com.party.guham2.model.user

import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class CheckUserApplicationStatusEntity(
    val id: Int,
    val message: String,
    val status: String,
    val createdAt: String,
): DataMapper<CheckUserApplicationStatus>{
    override fun toDomain(): CheckUserApplicationStatus {
        return CheckUserApplicationStatus(
            id = id,
            message = message,
            status = status,
            createdAt = createdAt
        )
    }
}
