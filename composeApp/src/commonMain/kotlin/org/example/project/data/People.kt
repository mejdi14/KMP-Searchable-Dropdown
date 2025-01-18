package org.example.project.data

import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_1
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_2
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_3
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_4
import kmp_searchable_dropdown.composeapp.generated.resources.fichier_5
import kmp_searchable_dropdown.composeapp.generated.resources.student1
import kmp_searchable_dropdown.composeapp.generated.resources.student2
import kmp_searchable_dropdown.composeapp.generated.resources.student3
import kmp_searchable_dropdown.composeapp.generated.resources.student4
import kmp_searchable_dropdown.composeapp.generated.resources.student5
import org.jetbrains.compose.resources.DrawableResource


data class People(
    val name: String,
    val photo: DrawableResource,
    val note: String
)

val people = listOf(
    People("Arij", Res.drawable.student2, "+93"),
    People("Mejdi", Res.drawable.student1, "+93"),
    People("Sami", Res.drawable.student3, "+93"),
    People("Rami", Res.drawable.student5, "+93"),
    People("Balqees", Res.drawable.student4, "+93"),
    )