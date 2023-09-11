package com.msg.presentation.view.self_study.component

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.SearchIcon
import com.dotori.dotori_components.theme.Transparent

@Composable
fun BottomSheetContent(
    onSearchLogic: (
        searchText: String,
        grade: String,
        `class`: String,
        gender: String
    ) -> Unit
) {
    var textValue by remember { mutableStateOf("") }

    var gradeFilterSelectedState by remember { mutableStateOf("null") }
    var classFilterSelectedState by remember { mutableStateOf("null") }
    var genderFilterSelectedState by remember { mutableStateOf("null") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp, horizontal = 20.dp)
    ) {
        BottomSheetHeaderContent(
            onFilterClear = {
                gradeFilterSelectedState = "null"
                classFilterSelectedState = "null"
                genderFilterSelectedState = "null"
            }
        ) { textValue = it }
        Spacer(modifier = Modifier.height(32.dp))
        BottomSheetBodyContent(
            gradeFilterSelectedState = gradeFilterSelectedState,
            classFilterSelectedState = classFilterSelectedState,
            genderFilterSelectedState = genderFilterSelectedState,
            onGradeChange = { gradeFilterSelectedState = it },
            onClassChange = { classFilterSelectedState = it },
            onGenderChange = { genderFilterSelectedState = it }
        ) {
            onSearchLogic(
                textValue,
                gradeFilterSelectedState,
                classFilterSelectedState,
                genderFilterSelectedState
            )
        }
    }
}

@Composable
fun BottomSheetHeaderContent(
    onFilterClear: () -> Unit,
    onTextValueChange: (String) -> Unit
) {
    var textValue by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = "필터",
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.clickable { onFilterClear() },
            text = "초기화",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20
        )
    }

    DotoriTextField(
        value = textValue,
        placeholder = "이름을 입력해 주세요",
        trailingIcon = { SearchIcon(tint = DotoriTheme.colors.neutral30) },
        onValueChange = {
            textValue = it
            onTextValueChange(textValue)
        }
    )
}

@Composable
fun BottomSheetBodyContent(
    gradeFilterSelectedState: String,
    classFilterSelectedState: String,
    genderFilterSelectedState: String,
    onGradeChange: (String) -> Unit,
    onClassChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onSearchIconClick: () -> Unit
) {
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = "학년",
        style = DotoriTheme.typography.smallTitle,
        color = DotoriTheme.colors.neutral10
    )

    Row {
        repeat(3) { index ->
            DotoriButton(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f),
                text = "${index + 1}",
                colors = buttonIsSelect(text = "${index + 1}", textState = gradeFilterSelectedState),
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { onGradeChange("${index + 1}") }
        }

        Spacer(modifier = Modifier.weight(1f))
    }

    Text(
        modifier = Modifier.padding(top = 32.dp, bottom = 8.dp),
        text = "반",
        style = DotoriTheme.typography.smallTitle,
        color = DotoriTheme.colors.neutral10
    )

    Row {
        repeat(4) { index ->
            DotoriButton(
                modifier = Modifier
                    .padding(end = if (index == 3) 0.dp else 8.dp)
                    .weight(1f),
                text = "${index + 1}",
                colors = buttonIsSelect(text = "${index + 1}", textState = classFilterSelectedState),
                paddingValues = PaddingValues(vertical = 8.dp),
                textStyle = DotoriTheme.typography.body2
            ) { onClassChange("${index + 1}") }
        }
    }

    Text(
        modifier = Modifier.padding(top = 32.dp, bottom = 8.dp),
        text = "성별",
        style = DotoriTheme.typography.smallTitle,
        color = DotoriTheme.colors.neutral10
    )

    Row {
        DotoriButton(
            modifier = Modifier
                .padding(end = 8.dp, bottom = 32.dp)
                .weight(1f),
            text = "남자",
            colors = buttonIsSelectGenderMan(textState = genderFilterSelectedState),
            paddingValues = PaddingValues(vertical = 8.dp),
            textStyle = DotoriTheme.typography.body2
        ) { onGenderChange("MAN") }

        DotoriButton(
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f),
            text = "여자",
            colors = buttonIsSelectGenderWoman(textState = genderFilterSelectedState),
            paddingValues = PaddingValues(vertical = 8.dp),
            textStyle = DotoriTheme.typography.body2
        ) { onGenderChange("WOMAN") }

        Spacer(modifier = Modifier.weight(2f))
    }

    DotoriButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        text = "확인",
        colors = DotoriTheme.colors.primary10,
        textStyle = DotoriTheme.typography.subTitle2
    ) {
        onSearchIconClick()
    }
}

@Composable
private fun buttonIsSelect(text: String, textState: String): Color {
    return if (text == textState) DotoriTheme.colors.primary10
    else Transparent
}

@Composable
private fun buttonIsSelectGenderMan(textState: String): Color {
    return if (textState == "MAN") DotoriTheme.colors.primary10
    else Transparent
}

@Composable
private fun buttonIsSelectGenderWoman(textState: String): Color {
    return if (textState == "WOMAN") DotoriTheme.colors.primary10
    else Transparent
}