package org.example.dropdown.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.cross_icon
import org.example.dropdown.data.listener.MultipleRemoveItemListener
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.reflect.KProperty1


@Composable
fun <T> DefaultItemHeaderComposable(
    backgroundColor: Color = Color(0xFFF5F6F8),
    shape: Shape = RoundedCornerShape(4.dp),
    paddingValues: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
    deleteIcon: DrawableResource = Res.drawable.cross_icon,
    iconColor: Color = Color.Black,
    textColor: Color = iconColor,
    spaceBetween: Dp = 5.dp,
    item: T,
    title: KProperty1<T, *>,
    removeItemListener: MultipleRemoveItemListener<T>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(color = backgroundColor, shape = shape)
            .padding(paddingValues)
    ) {
        Text(title.get(item).toString(), color = textColor)
        Spacer(Modifier.width(spaceBetween))
        Icon(
            painter = painterResource(deleteIcon), contentDescription = "",
            tint = iconColor,
            modifier = Modifier.padding(2.dp)
                .clickable {
                    removeItemListener.onRemove(item)
                }
        )
    }
}
