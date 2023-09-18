package com.msg.presentation.view.student_info.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.PenIcon
import com.dotori.dotori_components.theme.SearchIcon
import com.dotori.dotori_components.theme.SelfStudyAllowIcon
import com.dotori.dotori_components.theme.SelfStudyDisallowIcon
import com.dotori.dotori_components.theme.Transparent
import com.dotori.dotori_components.theme.XMarkIcon2

@Composable
fun StudentInfoBottomSheetContent(
    bottomSheetType: StudentInfoBottomSheetType,
    isBaned: Boolean,
    name: String,
    studentId: String,
    onModifyClick: () -> Unit,
    onSelfStudyClick: () -> Unit,
    onSaveClick: () -> Unit,
    onDismiss: () -> Unit,
    onSearchLogic: (
        searchText: String?,
        grade: String?,
        `class`: String?,
        gender: String?,
        role: String?,
        selfStudyCheck: Boolean?
    ) -> Unit
) {
    when (bottomSheetType) {
        StudentInfoBottomSheetType.Filter -> {
            FilterBottomSheet(
                onSearchLogic = onSearchLogic,
                onDismiss = onDismiss
            )
        }
        StudentInfoBottomSheetType.StudentInfo -> {
            StudentInfoBottomSheet(
                isBaned = isBaned,
                onModifyClick = onModifyClick,
                onSelfStudyClick = onSelfStudyClick
            )
        }
        else -> {
            ModifyStudentInfoBottomSheet(
                name = name,
                studentId = studentId,
                onSaveClick = onSaveClick,
                onDismiss = onDismiss
            )
        }
    }
}

@Composable
fun StudentInfoBottomSheet(
    isBaned: Boolean,
    onModifyClick: () -> Unit,
    onSelfStudyClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 20.dp,
            vertical = 32.dp
        ),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onModifyClick
                ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PenIcon(tint = DotoriTheme.colors.neutral20)
            Text(
                text = "정보 수정",
                style = DotoriTheme.typography.body,
                color = DotoriTheme.colors.neutral10
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onSelfStudyClick
                ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isBaned) SelfStudyAllowIcon() else SelfStudyDisallowIcon()
            Text(
                text = if (isBaned) "자습 금지 해제" else "자습 금지",
                style = DotoriTheme.typography.body,
                color = DotoriTheme.colors.neutral10
            )
        }
    }
}

@Composable
fun FilterBottomSheet(
    onSearchLogic: (
        searchText: String?,
        grade: String?,
        `class`: String?,
        gender: String?,
        role: String?,
        selfStudyCheck: Boolean?
    ) -> Unit,
    onDismiss: () -> Unit
) {
    var textValue: String? by remember { mutableStateOf(null) }
    var gradeFilterSelectedState: String? by remember { mutableStateOf(null) }
    var classFilterSelectedState: String? by remember { mutableStateOf(null) }
    var genderFilterSelectedState: Gender? by remember { mutableStateOf(null) }
    var roleFilterSelectedState: String? by remember { mutableStateOf(null) }
    var selfStudyCheckFilterSelectedState: Boolean? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier.padding(
            horizontal = 20.dp,
            vertical = 32.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "필터",
                style = DotoriTheme.typography.subTitle1,
                color = DotoriTheme.colors.neutral10
            )
            Text(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        textValue = null
                        gradeFilterSelectedState = null
                        classFilterSelectedState = null
                        genderFilterSelectedState = null
                        roleFilterSelectedState = null
                        selfStudyCheckFilterSelectedState = null
                    }
                ),
                text = "초기화",
                style = DotoriTheme.typography.body2,
                color = DotoriTheme.colors.neutral20
            )
        }
        DotoriTextField(
            value = textValue ?: "",
            placeholder = "이름을 입력해 주세요",
            trailingIcon = { SearchIcon(tint = DotoriTheme.colors.neutral30) },
            onValueChange = { textValue = it },
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "학년",
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(3) {
                val grade = (it + 1).toString()
                DotoriButton(
                    modifier = Modifier.weight(1f),
                    text = grade,
                    colors = if (grade == gradeFilterSelectedState) DotoriTheme.colors.primary10 else Transparent,
                    paddingValues = PaddingValues(vertical = 8.dp),
                    textStyle = DotoriTheme.typography.body2
                ) { gradeFilterSelectedState = grade }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "반",
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(4) {
                val classNum = (it + 1).toString()
                DotoriButton(
                    modifier = Modifier.weight(1f),
                    text = classNum,
                    colors = if (classNum == classFilterSelectedState) DotoriTheme.colors.primary10 else Transparent,
                    paddingValues = PaddingValues(vertical = 8.dp),
                    textStyle = DotoriTheme.typography.body2
                ) { classFilterSelectedState = classNum }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "성별",
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "남자",
                colors = if (genderFilterSelectedState == Gender.MAN) DotoriTheme.colors.primary10 else Transparent,
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { genderFilterSelectedState = Gender.MAN }
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "여자",
                colors = if (genderFilterSelectedState == Gender.WOMAN) DotoriTheme.colors.primary10 else Transparent,
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { genderFilterSelectedState = Gender.WOMAN }
            Spacer(modifier = Modifier.weight(2f))
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "직책",
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "학생",
                colors = if (roleFilterSelectedState == "ROLE_MEMBER") DotoriTheme.colors.primary10 else Transparent,
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { roleFilterSelectedState = "ROLE_MEMBER" }
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "개발자",
                colors = if (roleFilterSelectedState == "ROLE_DEVELOPER") DotoriTheme.colors.primary10 else Transparent,
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { roleFilterSelectedState = "ROLE_DEVELOPER" }
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "자치위원",
                colors = if (roleFilterSelectedState == "ROLE_COUNCILLOR") DotoriTheme.colors.primary10 else Transparent,
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { roleFilterSelectedState = "ROLE_COUNCILLOR" }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "자습",
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "자습금지",
                colors = if (selfStudyCheckFilterSelectedState == false) DotoriTheme.colors.primary10 else Transparent,
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { selfStudyCheckFilterSelectedState = false }
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "자습가능",
                colors = if (selfStudyCheckFilterSelectedState == true) DotoriTheme.colors.primary10 else Transparent,
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { selfStudyCheckFilterSelectedState = true }
            Spacer(modifier = Modifier.weight(2f))
        }
        Spacer(modifier = Modifier.weight(1f))
        DotoriButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            text = "확인",
            textStyle = DotoriTheme.typography.subTitle2
        ) {
            onSearchLogic(
                textValue,
                gradeFilterSelectedState,
                classFilterSelectedState,
                genderFilterSelectedState.toString(),
                roleFilterSelectedState,
                selfStudyCheckFilterSelectedState
            )
            onDismiss()
        }
    }
}

@Composable
fun ModifyStudentInfoBottomSheet(
    name: String,
    studentId: String,
    onDismiss: () -> Unit,
    onSaveClick: () -> Unit
) {
    val genderList = listOf("남", "여")
    val roleList = listOf("학생", "자치위원", "개발자")

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "학생 정보 수정",
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
        StudentInfoTextField(
            title = "이름",
            content = name
        )
        StudentInfoTextField(
            title = "학번",
            content = studentId
        )
        StudentInfoSegmentButton(
            title = "성별",
            list = genderList
        )
        StudentInfoSegmentButton(
            title = "직책",
            list = roleList
        )
        Spacer(modifier = Modifier.height(16.dp))
        DotoriButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            text = "저장",
            onClick = onSaveClick
        )
    }
}