package com.party.guham2.presentation.screens.guide_permission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY100
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.guide_permission.component.GuidePermissionDescriptionSection
import com.party.guham2.presentation.screens.guide_permission.component.GuidePermissionHeaderSection
import com.party.guham2.presentation.screens.guide_permission.component.GuidePermissionListSection
import com.party.guham2.presentation.screens.guide_permission.component.GuidePermissionTitleSection
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import dev.icerock.moko.permissions.notifications.REMOTE_NOTIFICATION
import kotlinx.coroutines.launch

@Composable
fun GuidePermissionScreenRoute(
    navController: NavHostController,
){
    val factory = rememberPermissionsControllerFactory()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val controller = remember(factory) {
        factory.createPermissionsController()
    }

    var isGranted by remember { mutableStateOf(false) }

    BindEffect(controller)

    LaunchedEffect(key1 = Unit){
        scope.launch {
            checkPermission(
                permission = Permission.REMOTE_NOTIFICATION,
                controller = controller,
                snackBarHostState = snackBarHostState,
                onGranted = { isGranted = true}
            )
        }
    }

    GuidePermissionScreen(
        onConfirm = {
            if(isGranted){
                navController.navigate(Screens.Login){
                    popUpTo(Screens.GuidePermission) { inclusive = true}
                }
            }
        }
    )
}

suspend fun checkPermission(
    permission: Permission,
    controller: PermissionsController,
    snackBarHostState: SnackbarHostState,
    onGranted: () -> Unit,
) {
    val granted = controller.isPermissionGranted(permission)
    if(!granted){
        try {
            controller.providePermission(permission)
            onGranted()
        }catch (e: DeniedException){
            val result = snackBarHostState.showSnackbar(
                message = "Denied",
                actionLabel = "Open Settings",
                duration = SnackbarDuration.Short
            )
            if(result == SnackbarResult.ActionPerformed){
                controller.openAppSettings()
            }
        }catch (e: DeniedAlwaysException){
            val result = snackBarHostState.showSnackbar(
                message = "Permanently Denied",
                actionLabel = "Open Settings",
                duration = SnackbarDuration.Short
            )
            if(result == SnackbarResult.ActionPerformed){
                controller.openAppSettings()
            }
        }catch (e: RequestCanceledException){
            snackBarHostState.showSnackbar(
                message = "Request Canceled",
            )
        }
    } else {
        onGranted()
    }
}

@Composable
private fun GuidePermissionScreen(
    onConfirm: () -> Unit,
){
    Scaffold(
        topBar = {
            GuidePermissionHeaderSection()
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
                .padding(it)
                .padding(horizontal = MEDIUM_PADDING_SIZE)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                HeightSpacer(32.dp)
                GuidePermissionTitleSection()
                HeightSpacer(40.dp)
                GuidePermissionListSection()
                HeightSpacer(20.dp)

                HorizontalDivider(
                    thickness = 1.dp,
                    color = GRAY100
                )

                HeightSpacer(20.dp)

                GuidePermissionDescriptionSection()
            }

            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                buttonText = "확인",
                buttonTextWeight = FontWeight.Bold,
                buttonTextSize = B2,
                containerColor = PRIMARY,
                buttonTextColor = BLACK,
                onClick = onConfirm
            )
            HeightSpacer(12.dp)
        }
    }
}
