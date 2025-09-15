package com.party.guham2.presentation.screens.party_detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.B2
import com.party.guham2.design.B3
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY100
import com.party.guham2.design.GRAY200
import com.party.guham2.design.ImageLoading
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.design.type.PartyAuthorityType
import com.party.guham2.presentation.model.party.PartyMemberModel
import com.party.guham2.presentation.model.party.PartyUsersModel
import com.party.guham2.presentation.model.party.toPartyMember
import com.party.guham2.presentation.model.user.PartyAuthorityModel
import com.party.guham2.presentation.screens.party_detail.state.PartyDetailState
import org.jetbrains.compose.resources.painterResource

@Composable
fun PartyDetailUserSection(
    state: PartyDetailState,
    onReports: (Int) -> Unit,
){
    PartyDetailUsersContent(
        partyUsers = state.partyUsers,
        authority = state.partyAuthority,
        onReports = onReports
    )
}

@Composable
private fun PartyDetailUsersContent(
    partyUsers: PartyUsersModel,
    authority: PartyAuthorityModel,
    onReports: (Int) -> Unit,
) {
    val partyAdminListSize = partyUsers.partyAdmin.size
    val partyUserListSize = partyUsers.partyUser.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        PartyDetailTitle(
            title = "파티원",
            number = "${partyAdminListSize + partyUserListSize}",
        )
        HeightSpacer(heightDp = 24.dp)

        PartyDetailUsersList(
            partyUsers = partyUsers,
            authority = authority,
            onReports = onReports
        )
    }
}

@Composable
fun PartyDetailUsersList(
    partyUsers: PartyUsersModel,
    authority: PartyAuthorityModel,
    onReports: (Int) -> Unit,
) {
    val partyAdminList = partyUsers.partyAdmin
    val partyUserList = partyUsers.partyUser

    val partyMemberList: List<PartyMemberModel> = partyAdminList.map { admin ->
        admin.toPartyMember()
    } + partyUserList.map { user ->
        user.toPartyMember()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
    ) {
        itemsIndexed(
            items = partyMemberList,
            key = { index, _ ->
                index
            }
        ){ _, item ->
            PartyDetailUsersListItem(
                partyMember = item,
                authority = authority,
                onReports = onReports
            )
        }
    }
}

@Composable
fun PartyDetailUsersListItem(
    partyMember: PartyMemberModel,
    authority: PartyAuthorityModel,
    onReports: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 5.dp),
            colors = CardDefaults.cardColors(
                containerColor = WHITE,
            ),
            shape = RoundedCornerShape(LARGE_CORNER_SIZE),
            elevation = CardDefaults.cardElevation(2.dp),
            border = BorderStroke(1.dp, GRAY100),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PartyDetailUserProfileImage(
                        imageUrl = partyMember.image,
                        authority = partyMember.authority,
                    )
                    WidthSpacer(widthDp = 12.dp)
                    PartyDetailUserInfo(
                        authority = partyMember.authority,
                        position = "${partyMember.main} ${partyMember.sub}",
                        nickName = partyMember.nickName,
                        userId = partyMember.userId,
                        authorityUserId = authority.id
                    )
                }

                // 나 인 경우는 신고하기 아이콘 노출X
                if(partyMember.userId != authority.id){
                    Image(
                        painter = painterResource(resource = DesignResources.Icon.icon_emergency),
                        contentDescription = "emergency",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 10.dp)
                            .noRippleClickable {
                                onReports(partyMember.userId)
                            }
                    )
                }
            }
        }
        HeightSpacer(heightDp = 12.dp)
    }
}

@Composable
private fun PartyDetailUserProfileImage(
    imageUrl: String,
    authority: String
){
    Box(
        modifier = Modifier
            .size(60.dp)
    ) {
        ImageLoading(
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp),
            imageUrl = imageUrl,
            borderColor = GRAY200,
            borderWidth = 1.dp,
            roundedCornerShape = 30.dp
        )
        if(authority == PartyAuthorityType.MASTER.authority){
            Image(
                painter = painterResource(resource = DesignResources.Icon.icon_master),
                contentDescription = "master",
                modifier = Modifier.size(24.dp).align(Alignment.BottomEnd),
                colorFilter = ColorFilter.tint(Color.Unspecified)
            )
        }
    }
}

@Composable
private fun PartyDetailUserInfo(
    authority: String,
    position: String,
    nickName: String,
    userId: Int,
    authorityUserId: Int,
){
    Column(
        modifier = Modifier
            .height(50.dp)
    ) {
        AuthorityAndPosition(
            authority = authority,
            position = position,
        )
        HeightSpacer(heightDp = 4.dp)

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(userId == authorityUserId){
                PointMe()
                WidthSpacer(widthDp = 4.dp)
            }
            CustomText(
                text = nickName,
                fontSize = B1
            )
        }
    }
}

@Composable
private fun PointMe() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(20.dp)
            .background(BLACK),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "나",
            color = WHITE,
            fontSize = B3,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun AuthorityAndPosition(
    authority: String,
    position: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(authority == PartyAuthorityType.MASTER.authority){
            CustomText(
                text = "파티장",
                fontSize = B2,
                fontWeight = FontWeight.SemiBold,
                color = PRIMARY,
            )
        }
        WidthSpacer(widthDp = 6.dp)
        Image(
            painter = painterResource(DesignResources.Image.image_vertical),
            contentDescription = "",
            modifier = Modifier
                .width(1.dp)
                .height(8.dp)
                .background(GRAY200),
        )
        WidthSpacer(widthDp = 6.dp)
        if(authority == PartyAuthorityType.DEPUTY.authority){
            CustomText(
                text = "부파티장 | ",
                fontSize = B2,
                fontWeight = FontWeight.SemiBold,
                color = PRIMARY,
            )
        }
        CustomText(
            text = position,
            fontSize = B2,
            fontWeight = FontWeight.SemiBold,
        )
    }
}