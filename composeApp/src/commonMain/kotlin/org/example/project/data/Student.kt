package org.example.project.data


data class Student(
    val name: String,
    val age: Int,
    val note: String
)

val students = listOf(
    Student("Alice", 20, "Good student"),
    Student("Bob", 22, "Needs improvement"),
    Student("Charlie", 21, "Excellent work"),
    Student("David", 23, "Consistent progress"),
    Student("Eve", 19, "Quick learner"),
    Student("Frank", 24, "Great team player"),
    Student("Grace", 20, "Creative thinker"),
    Student("Hannah", 22, "Highly motivated"),
    Student("Isaac", 23, "Shows leadership"),
    Student("Jack", 21, "Hardworking"),
    Student("Karen", 20, "Very diligent"),
    Student("Liam", 22, "Enthusiastic learner")
)
