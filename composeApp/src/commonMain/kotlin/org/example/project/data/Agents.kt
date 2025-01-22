package org.example.project.data

import androidx.compose.ui.graphics.Color
import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_1
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_2
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_3
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_4
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_5
import kmp_searchable_dropdown.composeapp.generated.resources.image1
import kmp_searchable_dropdown.composeapp.generated.resources.image2
import kmp_searchable_dropdown.composeapp.generated.resources.image5
import kmp_searchable_dropdown.composeapp.generated.resources.image6
import kmp_searchable_dropdown.composeapp.generated.resources.student1
import kmp_searchable_dropdown.composeapp.generated.resources.student2
import kmp_searchable_dropdown.composeapp.generated.resources.student3
import kmp_searchable_dropdown.composeapp.generated.resources.student4
import kmp_searchable_dropdown.composeapp.generated.resources.student5
import org.jetbrains.compose.resources.DrawableResource


data class Agent(
    val name: String,
    val photo: DrawableResource,
    val backgroundColor: Color,
    val textColor: Color
)

val agents = listOf(
    Agent("Élodie", Res.drawable.image1, Color(0xFFfdf8ec), Color(0xFFc76433)),
    Agent("Lucas", Res.drawable.image2, Color(0xFFf0fdf6), Color(0xFF38905a)),
    Agent("Camille", Res.drawable.image6, Color(0xFFe6fbff), Color(0xFF7acbd3)),
    Agent("Mathis", Res.drawable.image2, Color(0xFFfdf8ec), Color(0xFFc76433)),
    Agent("Chloé", Res.drawable.image5, Color(0xFFfdf8ec), Color(0xFFc76433)),
)
