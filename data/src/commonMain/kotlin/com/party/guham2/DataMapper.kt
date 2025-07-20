package com.party.guham2

internal interface DataMapper<DomainModel> {
    fun toDomain(): DomainModel
}