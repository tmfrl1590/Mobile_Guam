package com.party.guham2.presentation.model.recruitment

import com.party.guham2.model.recruitment.RecruitmentDetail
import com.party.guham2.model.recruitment.RecruitmentDetailParty
import com.party.guham2.model.recruitment.RecruitmentDetailPartyType
import com.party.guham2.model.recruitment.RecruitmentDetailPosition

data class RecruitmentDetailModel(
    val party: RecruitmentDetailPartyModel = RecruitmentDetailPartyModel(),
    val position: RecruitmentDetailPositionModel = RecruitmentDetailPositionModel(),
    val content: String = "",
    val recruitingCount: Int = 0,
    val recruitedCount: Int = 0,
    val applicationCount: Int = 0,
    val createdAt: String = "",
)


data class RecruitmentDetailPartyModel(
    val title: String = "",
    val image: String? = null,
    val status: String = "",
    val partyType: RecruitmentDetailPartyTypeModel = RecruitmentDetailPartyTypeModel(),
)

data class RecruitmentDetailPartyTypeModel(
    val type: String = "",
)

data class RecruitmentDetailPositionModel(
    val id: Int = 0,
    val main: String = "",
    val sub: String = "",
)

fun RecruitmentDetail.toPresentation(): RecruitmentDetailModel = RecruitmentDetailModel(
    party = party.toPresentation(),
    position = position.toPresentation(),
    content = content,
    recruitingCount = recruitingCount,
    recruitedCount = recruitedCount,
    applicationCount = applicationCount,
    createdAt = createdAt
)

fun RecruitmentDetailParty.toPresentation(): RecruitmentDetailPartyModel = RecruitmentDetailPartyModel(
    title = title,
    image = image,
    status = status,
    partyType = partyType.toPresentation()
)

fun RecruitmentDetailPartyType.toPresentation(): RecruitmentDetailPartyTypeModel = RecruitmentDetailPartyTypeModel(
    type = type
)

fun RecruitmentDetailPosition.toPresentation(): RecruitmentDetailPositionModel = RecruitmentDetailPositionModel(
    id = id,
    main = main,
    sub = sub
)