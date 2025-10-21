package com.party.guham2.presentation.screens.active

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.tab_area.stateTabList
import com.party.guham2.design.type.OrderDescType
import com.party.guham2.design.type.SortType
import com.party.guham2.design.type.StatusType
import com.party.guham2.presentation.screens.active.action.MyPartyAction
import com.party.guham2.presentation.screens.active.component.ActiveTabSection
import com.party.guham2.presentation.screens.active.component.ActiveTitleSection
import com.party.guham2.presentation.screens.active.component.MyPartySection
import com.party.guham2.presentation.screens.active.component.MyRecruitmentSection
import com.party.guham2.presentation.screens.active.event.ActiveEvent
import com.party.guham2.presentation.screens.active.state.MyPartyState
import com.party.guham2.presentation.screens.active.viewmodel.ActiveViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.get

@Composable
fun ActiveScreenRoute(
    onStartScroll: (Boolean) -> Unit,
    onGoToSearch: () -> Unit,
    onGotoNotification: () -> Unit,
    onGotoPartyDetail: (Int) -> Unit,
    viewModel: ActiveViewModel = koinViewModel()
){
    LaunchedEffect(key1 = Unit) {
        viewModel.getMyRecruitment(1, 50, SortType.CREATED_AT.type, OrderDescType.DESC.type)
    }

    val myPartyState by viewModel.myPartyState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val isFabVisible by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    onStartScroll(isFabVisible)

    // 전체/진행중/종료 탭 선택 이벤트
    LaunchedEffect(key1 = myPartyState.selectedStatus) {
        val status = when(myPartyState.selectedStatus){
            "전체" -> null
            StatusType.ACTIVE.toDisplayText() -> StatusType.ACTIVE.type
            StatusType.ARCHIVED.toDisplayText() -> StatusType.ARCHIVED.type
            else -> null
        }
        viewModel.getMyParty(1, 50, SortType.CREATED_AT.type, OrderDescType.DESC.type, status = status)
    }

    LaunchedEffect(Unit) {
        ActiveEvent.scrollToUp.collectLatest {
            listState.animateScrollToItem(0)
        }
    }


    ActiveScreen(
        myPartyState = myPartyState,
        listState = listState,
        onGoToSearch = onGoToSearch,
        onGotoNotification = onGotoNotification,
        onAction = { action ->
            viewModel.onAction(action = action)
        },
        onMyPartyCardClick = onGotoPartyDetail
    )
}

@Composable
private fun ActiveScreen(
    myPartyState: MyPartyState,
    listState: LazyListState,
    onGoToSearch: () -> Unit,
    onGotoNotification: () -> Unit,
    onAction: (MyPartyAction) -> Unit,
    onMyPartyCardClick: (Int) -> Unit,
){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                ActiveTitleSection(
                    onGoToSearch = onGoToSearch,
                    onGotoNotification = onGotoNotification
                )
            }
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WHITE)
                    .padding(it)
                    .padding(horizontal = MEDIUM_PADDING_SIZE),
            ) {
                ActiveTabSection(
                    stateTabList = stateTabList,
                    selectedTabText = myPartyState.selectedTabText,
                    onTabClick = { selectedTab -> onAction(MyPartyAction.OnSelectTab(selectedTabText = selectedTab))}
                )

                if (myPartyState.selectedTabText == stateTabList[0]) {
                    MyPartySection(
                        listState = listState,
                        myPartyState = myPartyState,
                        onSelectStatus = { status -> onAction(MyPartyAction.OnSelectStatus(status)) },
                        onChangeOrderBy = { orderByDesc -> onAction(MyPartyAction.OnOrderByChange(orderByDesc)) },
                        onClick = onMyPartyCardClick,
                    )
                } else {
                    MyRecruitmentSection(
                        listState = listState,
                        myPartyState = myPartyState,
                        onSelectRecruitmentTab = { selectedRecruitmentTab -> onAction(MyPartyAction.OnSelectRecruitmentTab(selectedRecruitmentTab)) },
                        onShowHelpCard = { iShow -> onAction(MyPartyAction.OnShowHelpCard(isShowHelpCard = iShow)) },
                        onChangeOrderBy = { orderByDesc -> onAction(MyPartyAction.OnRecruitmentOrderByChange(orderByDesc)) },
                        onRefusal = { partyApplicationId, partyId -> onAction(MyPartyAction.OnRejectionParty(partyId = partyId, partyApplicationId = partyApplicationId)) },
                        onAccept = { partyApplicationId, partyId -> onAction(MyPartyAction.OnApprovalParty(partyId = partyId, partyApplicationId = partyApplicationId)) },
                        onCancel = { partyId, partyApplicationId -> onAction(MyPartyAction.OnCancelRecruitment(partyId = partyId, partyApplicationId = partyApplicationId))}
                    )
                }
            }
        }
    }
}