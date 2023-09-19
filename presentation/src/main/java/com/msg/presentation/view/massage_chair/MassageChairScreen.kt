package com.msg.presentation.view.massage_chair

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotori.dotori_components.components.card.DotoriStudentCard
import com.dotori.dotori_components.components.utils.RoleType
import com.dotori.dotori_components.components.utils.Types
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.domain.model.massage.MassageListResponseModel
import com.msg.presentation.view.component.DotoriTopBar
import com.msg.presentation.view.massage_chair.component.EmptyMassageChairScreen
import com.msg.presentation.view.massage_chair.component.MassageChairTopBar
import com.msg.presentation.view.util.updateDotoriTheme
import com.msg.presentation.viewmodel.MassageViewModel

@Composable
fun MassageChairScreen(massageViewModel: MassageViewModel = hiltViewModel()) {
    val massageList by massageViewModel.getMassageRank.collectAsState()
    val role by massageViewModel.roleState.collectAsState()
    
    LaunchedEffect(Unit) {
        massageViewModel.getRole()
        massageViewModel.getMassageRank(role.data!!)
    }

    if (massageList.data.isNullOrEmpty()) MassageChairIsEmptyContent()
    else MassageChairStudentListContent(
        role = role.data!!,
        list = massageList.data!!
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MassageChairStudentListContent(
    role: String,
    list: List<MassageListResponseModel>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { DotoriTopBar(onSwitchClick = { DotoriTheme.updateDotoriTheme() }) }

        item {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = DotoriTheme.colors.background
            )
        }

        stickyHeader { MassageChairTopBar() }

        itemsIndexed(list) { position, item ->
            DotoriStudentCard(
                name = item.memberName,
                gender = item.gender,
                role = role.changeRoleToEnum(),
                studentNumber = item.stuNum,
                position = position,
                mode = Types.CardType.MASSAGE_CHAIR,
                isLast = list.lastIndex + 1 == position,
                onCheckBoxChange = {}
            )
        }
    }
}

@Composable
fun MassageChairIsEmptyContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        DotoriTopBar(onSwitchClick = { DotoriTheme.updateDotoriTheme() })
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DotoriTheme.colors.background
        )
        MassageChairTopBar()
        EmptyMassageChairScreen()
    }
}

private fun String.changeRoleToEnum(): String {
    return when(this) {
        "member" -> RoleType.ROLE_MEMBER.toString()
        "developer" -> RoleType.ROLE_DEVELOPER.toString()
        "councillor" -> RoleType.ROLE_COUNCILLOR.toString()
        "admin" -> RoleType.ROLE_ADMIN.toString()
        else -> "null"
    }
}

@Preview
@Composable
private fun MassageChairScreenPreview() {
    MassageChairScreen()
}