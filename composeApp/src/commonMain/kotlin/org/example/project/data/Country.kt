package org.example.project.data

data class Country(
    val name: String,
    val flagUrl: String,
    val phoneCode: String
)

val baseUrl = "https://www.countryflags.com/wp-content/uploads"


val countries = listOf(
    Country("Afghanistan", "$baseUrl/afghanistan-flag-png-large.png", "+93"),
    Country("Albania", "$baseUrl/albania-flag-png-large.png", "+355"),
    Country("Algeria", "$baseUrl/algeria-flag-png-large.png", "+213"),
    Country("Andorra", "$baseUrl/andorra-flag-png-large.png", "+376"),
    Country("Angola", "$baseUrl/angola-flag-png-large.png", "+244"),
    Country("Argentina", "$baseUrl/argentina-flag-png-large.png", "+54"),
    Country("Armenia", "$baseUrl/armenia-flag-png-large.png", "+374"),
    Country("Australia", "$baseUrl/australia-flag-png-large.png", "+61"),
    Country("Austria", "$baseUrl/austria-flag-png-large.png", "+43"),
    Country("Azerbaijan", "$baseUrl/azerbaijan-flag-png-large.png", "+994"),
    Country("Bahamas", "$baseUrl/bahamas-flag-png-large.png", "+1242"),
    Country("Bahrain", "$baseUrl/bahrain-flag-png-large.png", "+973"),
    Country("Bangladesh", "$baseUrl/bangladesh-flag-png-large.png", "+880"),
    Country("Barbados", "$baseUrl/barbados-flag-png-large.png", "+1246"),
    Country("Belarus", "$baseUrl/belarus-flag-png-large.png", "+375"),
    Country("Belgium", "$baseUrl/belgium-flag-png-large.png", "+32"),
    Country("Belize", "$baseUrl/belize-flag-png-large.png", "+501"),
    Country("Benin", "$baseUrl/benin-flag-png-large.png", "+229"),
    Country("Bhutan", "$baseUrl/bhutan-flag-png-large.png", "+975"),
    Country("Bolivia", "$baseUrl/bolivia-flag-png-large.png", "+591"),
    Country("Bosnia and Herzegovina", "$baseUrl/bosnia-and-herzegovina-flag-png-large.png", "+387"),
    Country("Botswana", "$baseUrl/botswana-flag-png-large.png", "+267"),
    Country("Brazil", "$baseUrl/brazil-flag-png-large.png", "+55"),
    Country("Brunei", "$baseUrl/brunei-flag-png-large.png", "+673"),
    Country("Bulgaria", "$baseUrl/bulgaria-flag-png-large.png", "+359"),
    Country("Burkina Faso", "$baseUrl/burkina-faso-flag-png-large.png", "+226"),
    Country("Burundi", "$baseUrl/burundi-flag-png-large.png", "+257"),
    Country("Cambodia", "$baseUrl/cambodia-flag-png-large.png", "+855"),
    Country("Cameroon", "$baseUrl/cameroon-flag-png-large.png", "+237"),
    Country("Canada", "$baseUrl/canada-flag-png-large.png", "+1"),
    Country("Chile", "$baseUrl/chile-flag-png-large.png", "+56"),
    Country("China", "$baseUrl/china-flag-png-large.png", "+86"),
    Country("Colombia", "$baseUrl/colombia-flag-png-large.png", "+57"),
    Country("Costa Rica", "$baseUrl/costa-rica-flag-png-large.png", "+506"),
    Country("Croatia", "$baseUrl/croatia-flag-png-large.png", "+385"),
    Country("Cuba", "$baseUrl/cuba-flag-png-large.png", "+53"),
    Country("Cyprus", "$baseUrl/cyprus-flag-png-large.png", "+357"),
    Country("Czech Republic", "$baseUrl/czech-republic-flag-png-large.png", "+420")
)
