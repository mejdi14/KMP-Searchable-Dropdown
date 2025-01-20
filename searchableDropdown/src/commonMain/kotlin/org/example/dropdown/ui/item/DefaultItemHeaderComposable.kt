package org.example.dropdown.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.cross_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.reflect.KProperty1


@Composable
fun <T> DefaultItemHeaderComposable(
    backgroundColor: Color = Color(0xFFF5F6F8),
    shape: Shape = RoundedCornerShape(4.dp),
    deleteIcon: DrawableResource = Res.drawable.cross_icon,
    iconColor: Color = Color.Black,
    item: T,
    title: KProperty1<T, *>,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(color = backgroundColor, shape = shape)
    ) {
        Text(title.get(item).toString())
        Spacer(Modifier.width(5.dp))
        Icon(
            painter = painterResource(deleteIcon), contentDescription = "",
            tint = iconColor
        )
    }
}
