package org.example.project.data

import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.student1
import kmp_searchable_dropdown.composeapp.generated.resources.student2
import kmp_searchable_dropdown.composeapp.generated.resources.student3
import kmp_searchable_dropdown.composeapp.generated.resources.student4
import kmp_searchable_dropdown.composeapp.generated.resources.student5
import org.jetbrains.compose.resources.DrawableResource


data class People(
    val name: String,
    val photo: DrawableResource,
    val job: String
)

val people = listOf(
    People("Arij", Res.drawable.student2, "Software engineer"),
    People("Mejdi", Res.drawable.student1, "Software engineer"),
    People("Sami", Res.drawable.student3, "Designer"),
    People("Rami", Res.drawable.student5, "Product Manager"),
    People("Balqees", Res.drawable.student4, "QA Tester"),
    )