package com.msg.presentation.view.rule_violation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.components.rule.DotoriRoleViolateItem
import com.dotori.dotori_components.components.utils.RoleViolateType
import com.dotori.dotori_components.theme.CalendarIcon
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.PlusIcon
import com.dotori.dotori_components.theme.Transparent
import com.dotori.dotori_components.theme.XMarkIcon
import com.dotori.dotori_components.theme.XMarkIcon2

@Composable
fun RuleViolationDialogContent(
    ruleViolationDialogType: RuleViolationDialogType,
    onDismiss: () -> Unit
) {
    when (ruleViolationDialogType) {
        RuleViolationDialogType.LIST -> {
            RuleViolationListDialogContent(onDismiss)
        }

        RuleViolationDialogType.CHECK -> {
            RuleViolationCheckDialogContent(onDismiss)
        }

        RuleViolationDialogType.CREATE -> {
            RuleViolationCreateDialogContent(onDismiss)
        }

        RuleViolationDialogType.DELETE -> {
            RuleViolationDeleteDialogContent(
                ruleViolation = "타호실 출입",
                onSubmit = {},
                onDismiss = onDismiss
            )
        }
    }
}

@Composable
fun RuleViolationListDialogContent(onDismiss: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "규정 위반 내역",
                style = DotoriTheme.typography.subTitle1,
                color = DotoriTheme.colors.neutral10
            )
            XMarkIcon2(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                ),
                tint = DotoriTheme.colors.neutral20
            )
        }
        LazyColumn(
            modifier = Modifier.padding(bottom = 56.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(3) {
                DotoriRoleViolateItem(violateText = "FIREARMS", violateDate = "2023-08-29") {}
            }
        }
        DotoriButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "확인",
            onClick = onDismiss
        )
    }
}

@Composable
fun RuleViolationCheckDialogContent(onDismiss: () -> Unit) {
    var isDetailClicked by remember { mutableStateOf(-1) }
    val ruleViolationList = listOf(
        "금지 물품 반입",
        "금지 물품 사용",
        "사감 지도 불이행",
        "시간 관 소홀 및 이탈 행위",
        "물품 훼손 및 절도",
        "취침 방해",
        "공동 생활 방해 및 위생 상태 불량",
        "애정 행위",
        "기숙사 출입 금지 구역 출입",
        "학습실 면학분위기 저해",
        "외부인 출입 관여"
    )
    val detailRuleViolationList = listOf(
        listOf( // 금지 물품 반입
            "• 반입 - 화기류",
            "• 반입 - 흉기",
            "• 반입 - 주류",
            "• 반입 - 담배",
            "• 반입 - 사행성기구",
            "• 반입 - 음식"
        ),
        listOf( // 금지 물품 사용
            "• 사용 - 화기류",
            "• 사용 - 흉기",
            "• 사용 - 주류",
            "• 사용 - 담배",
            "• 사용 - 사행성기구",
            "• 사용 - 음식",
        ),
        listOf( // 사감 지도 불이행
            "• 사감의 학습 및 생활지도 불이행"
        ),
        listOf( // 시간 관리 소홀 및 이탈 행위
            "• 지각",
            "• 외출",
            "• 외박"
        ),
        listOf( // 물품 훼손 및 절도
            "• 물품 훼손",
            "• 절도",
            "• 갈취",
        ),
        listOf( // 취침 방해
            "• 타호실 출입",
            "• 전자기기 소지",
            "• 고성방가",
        ),
        listOf( // 공동 생활 방해 및 위생 상태 불량
            "• 지정시간 외 기숙사 출입",
            "• 세탁기,건조기에 세탁물 방치",
            "• 공공시설물 사용규정 위반",
            "• 부착된 게시물 훼손 및 낙서",
            "• 전자기기 소지 혹은 사용",
            "• 호실 정리정돈 상태 불량",
            "• 기숙사 환경 오염"
        ),
        listOf( // 애정 행위
            "• 애정 행위",
            "• 성적 행위"
        ),
        listOf( // 기숙사 출입 금지 구역 출입
            "• 출입금지 구역 출입"
        ),
        listOf( // 학습실 면학분위기 저해
            "• 학습실 면학분위기 저해"
        ),
        listOf( // 외부인 출입 관여
            "• 외부인 출입 허가"
        )
    )
    var ruleViolationToggleList = remember { mutableStateListOf<String>() }
    val roleTypeMap = mapOf(
        "반입 - 화기류" to RoleViolateType.FIREARMS,
        "반입 - 흉기" to RoleViolateType.WEAPON,
        "반입 - 주류" to RoleViolateType.ALCOHOL,
        "반입 - 담배" to RoleViolateType.TOBACCO,
        "반입 - 사행성기구" to RoleViolateType.MEANDERING_APPARATUS,
        "반입 - 음식" to RoleViolateType.FOOD,
        "사용 - 화기류" to RoleViolateType.USE_FIREARMS,
        "사용 - 흉기" to RoleViolateType.USE_WEAPON,
        "사용 - 주류" to RoleViolateType.DRINKING_ALCOHOL,
        "사용 - 담배" to RoleViolateType.USE_TOBACCO,
        "사용 - 사행성기구" to RoleViolateType.USE_MEANDERING_APPARATUS,
        "사용 - 음식" to RoleViolateType.EAT_FOOD,
        "사감의 학습 및 생활지도 불이행" to RoleViolateType.MANAGER_GUIDANCE,
        "지각" to RoleViolateType.TIME,
        "외출" to RoleViolateType.OUTING,
        "외박" to RoleViolateType.OVERNIGHT_STAY,
        "물품 훼손" to RoleViolateType.DAMAGE_OF_GOODS,
        "절도" to RoleViolateType.THEFT,
        "갈취" to RoleViolateType.CHANTAGE,
        "타호실 출입" to RoleViolateType.DISTURBING_SLEEP,
        "전자기기 소지" to RoleViolateType.ELECTRONIC_DEVICE,
        "고성방가" to RoleViolateType.LOUD,
        "지정시간 외 기숙사 출입" to RoleViolateType.INCORRECT_ENTRY,
        "세탁기,건조기에 세탁물 방치" to RoleViolateType.LAUNDRY,
        "공공시설물 사용규정 위반" to RoleViolateType.VIOLATION_OF_THE_USE_OF_FACILITIES,
        "부착된 게시물 훼손 및 낙서" to RoleViolateType.DAMAGE_OF_POST,
        "전자기기 소지 혹은 사용" to RoleViolateType.POSSESSION_OF_ELECTRONICS_DEVICES,
        "호실 정리정돈 상태 불량" to RoleViolateType.CLEAN_COUNDITION_BAD,
        "기숙사 환경 오염" to RoleViolateType.ENVIRONMENT_POLLUTION,
        "애정 행위" to RoleViolateType.AFFECTIONATE_ACT,
        "성적 행위" to RoleViolateType.SEXUAL_ACT,
        "출입금지 구역 출입" to RoleViolateType.ENTRY_TO_PROHIBITED_AREAS,
        "학습실 면학분위기 저해" to RoleViolateType.VIOLATION_OF_STUDY_ROOM_RULES,
        "외부인 출입 허가" to RoleViolateType.OUTSIDER_ENTRY,
    )

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "규정 위반 기록",
                style = DotoriTheme.typography.subTitle1,
                color = DotoriTheme.colors.neutral10
            )
            XMarkIcon2(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                ),
                tint = DotoriTheme.colors.neutral20
            )
        }
        if (isDetailClicked < 0) {
            Column(
                modifier = Modifier.height(486.dp)
            ) {
                ruleViolationList.forEachIndexed { index, text ->
                    RuleViolationListItem(text = text) { isDetailClicked = index }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(486.dp)
                    .background(
                        color = DotoriTheme.colors.background,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 14.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                detailRuleViolationList[isDetailClicked].forEach {
                    val interactionSource = remember { MutableInteractionSource() }
                    val isClicked by interactionSource.collectIsPressedAsState()

                    Text(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { ruleViolationToggleList.add(it.replace("• ", "")) }
                        ),
                        text = it,
                        style = DotoriTheme.typography.smallTitle,
                        color = if (isClicked) DotoriTheme.colors.neutral10 else DotoriTheme.colors.neutral20
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            thickness = 1.dp,
            color = DotoriTheme.colors.neutral40
        )
        if (ruleViolationToggleList.isEmpty()) {
            Spacer(modifier = Modifier.height(80.dp))
        } else {
            LazyRow(
                modifier = Modifier.padding(vertical = 9.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ruleViolationToggleList) {
                    RuleViolationToggle(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { ruleViolationToggleList.remove(it) }
                        ),
                        violateText = roleTypeMap[it].toString()
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(2) {
                DotoriButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    text = if (it == 0) "이전" else "확인",
                    colors = if (it == 0) Transparent else DotoriTheme.colors.primary10,
                    textStyle = DotoriTheme.typography.subTitle2
                ) {
                    if (it == 0) isDetailClicked = -1
                    else onDismiss()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RuleViolationCreateDialogContent(onDismiss: () -> Unit) {
    Box(modifier = Modifier.height(520.dp)) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "규정 위반 기록",
                    style = DotoriTheme.typography.subTitle1,
                    color = DotoriTheme.colors.neutral10
                )
                XMarkIcon2(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDismiss
                    ),
                    tint = DotoriTheme.colors.neutral20
                )
            }
            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    item {
                        RuleViolationTextItem(
                            title = "학생",
                            content = "선민재, 박영재, 강경민, 조재영",
                            icon = {
                                PlusIcon(
                                    modifier = Modifier.clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = onDismiss
                                    )
                                )
                            }
                        )
                    }
                    item {
                        RuleViolationTextItem(
                            title = "날짜",
                            content = "2023년 8월 12일 (오늘)",
                            icon = { CalendarIcon() }
                        )
                    }
                    item {
                        Column(
                            modifier = Modifier.padding(bottom = 80.dp), // 일단 임시로 정해서 padding을 했습니다
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            repeat(3) {
                                RuleViolationTextItem(
                                    title = if (it == 0) "규정 위반 내용" else "",
                                    content = "반입 - 화기류",
                                    icon = { XMarkIcon() }
                                )
                            }
                            RuleViolationTextItem(
                                content = "추가하기",
                                textColor = DotoriTheme.colors.neutral30,
                                icon = {
                                    PlusIcon(
                                        modifier = Modifier.clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            onClick = onDismiss
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DotoriTheme.colors.cardBackground)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(2) {
                DotoriButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    text = if (it == 0) "취소" else "부여",
                    colors = if (it == 0) Transparent else DotoriTheme.colors.primary10,
                    textStyle = DotoriTheme.typography.subTitle2
                ) { onDismiss() }
            }
        }
    }
}

@Composable
fun RuleViolationDeleteDialogContent(
    ruleViolation: String,
    onSubmit: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "규정 위반 내역 삭제",
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )
        Text(
            modifier = Modifier.height(30.dp), // 미리 2줄 들어갈 정도의 height를 지정 (디자인 참고)
            text = "'$ruleViolation' 위반 내역을 삭제 하시겠습니까?",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20,
            maxLines = 2
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "취소",
                colors = Transparent,
                paddingValues = PaddingValues(6.dp),
                onClick = onDismiss
            )
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "확인",
                paddingValues = PaddingValues(6.dp),
                onClick = onSubmit
            )
        }
    }
}

@Preview
@Composable
fun RuleViolationListDialogPreview() {
    RuleViolationDialogContent(RuleViolationDialogType.CHECK) {}
}
