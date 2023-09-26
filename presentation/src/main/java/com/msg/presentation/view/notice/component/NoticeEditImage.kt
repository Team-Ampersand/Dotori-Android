package com.msg.presentation.view.notice.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.ExclamationMarkIcon
import com.msg.domain.model.notice.response.NoticeImageModel
import com.msg.presentation.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NoticeEditImage(
    modifier: Modifier = Modifier,
    images: List<NoticeImageModel>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "사진",
                style = DotoriTheme.typography.subTitle1,
                color = DotoriTheme.colors.neutral10
            )
            ExclamationMarkIcon() /* TODO: 클릭 이벤트 생기면 추가 작업 */
        }
        LazyRow {
            item {
                Image(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { /* TODO: 갤러리에서 이미지 불러오는 로직 */ }
                    ),
                    painter = painterResource(R.drawable.ic_add_image),
                    contentDescription = "이미지 추가"
                )
            }
            items(images) {
                GlideImage(
                    modifier = Modifier.size(192.dp),
                    imageModel = { it.url },
                    previewPlaceholder = R.drawable.ic_empty_music_icon_light
                )
            }
        }
    }
}
