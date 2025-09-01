package com.party.guham2.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.party.guham2.design.B3
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY200
import com.party.guham2.design.GRAY400
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavigationBar(
    currentMainTab: MainTab,
    navController: NavHostController,
    onTabClick: (MainTab) -> Unit,
){
    AppBottomNavigationBar(
        show = navController.shouldShowBottomBar,
        modifier = Modifier
            .windowInsetsPadding(insets = WindowInsets.navigationBars)
        ,
        content = {
            bottomDestinations.forEach { mainTab ->
                AppBottomNavigationBarItem(
                    icon = mainTab.tabIcon,
                    text = mainTab.tabName,
                    isSelected = currentMainTab == mainTab,
                    onTabClick = { onTabClick(mainTab) },
                )
            }
        }
    )
}

@Composable
fun AppBottomNavigationBar(
    modifier: Modifier = Modifier,
    show: Boolean,
    content: @Composable (RowScope.() -> Unit)
) {
    Surface(
        color = WHITE,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .windowInsetsPadding(BottomAppBarDefaults.windowInsets)
    ) {
        if (show) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    color = GRAY200,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = content
                )
            }
        }
    }
}

@Composable
fun RowScope.AppBottomNavigationBarItem(
    onTabClick: () -> Unit,
    icon: DrawableResource,
    text: String,
    isSelected: Boolean
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .background(WHITE)
            .noRippleClickable(
                onClick = onTabClick,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            painter = painterResource(resource = icon) ,
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
            ,
            tint = if(isSelected) PRIMARY else GRAY400
        )
        CustomText(
            text = text,
            color = if(isSelected) BLACK else GRAY400,
            fontWeight = FontWeight(600),
            fontSize = B3
        )
    }
}

fun NavBackStackEntry?.toMainTab(): MainTab = when {
    this?.destination?.hasRoute<Screens.Active>()  == true -> MainTab.Active
    this?.destination?.hasRoute<Screens.Profile>() == true -> MainTab.Profile
    else -> MainTab.Home
}

private val NavController.shouldShowBottomBar
    get() = when (this.currentBackStackEntry.toMainTab()) {
        MainTab.Home,
        MainTab.Active,
        MainTab.Profile,
            -> true
    }

val bottomDestinations = listOf(
    MainTab.Home,
    MainTab.Active,
    MainTab.Profile
)