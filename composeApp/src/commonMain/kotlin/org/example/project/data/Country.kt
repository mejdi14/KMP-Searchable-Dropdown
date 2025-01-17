package org.example.project.data

import kmp_searchable_dropdown.composeapp.generated.resources.Res
import kmp_searchable_dropdown.composeapp.generated.resources.afghanistan
import kmp_searchable_dropdown.composeapp.generated.resources.albania
import kmp_searchable_dropdown.composeapp.generated.resources.brazil
import kmp_searchable_dropdown.composeapp.generated.resources.brunei
import kmp_searchable_dropdown.composeapp.generated.resources.bulgaria
import kmp_searchable_dropdown.composeapp.generated.resources.burkina_faso
import kmp_searchable_dropdown.composeapp.generated.resources.burundi
import kmp_searchable_dropdown.composeapp.generated.resources.cambodia
import kmp_searchable_dropdown.composeapp.generated.resources.cameroon
import kmp_searchable_dropdown.composeapp.generated.resources.canada
import kmp_searchable_dropdown.composeapp.generated.resources.chile
import kmp_searchable_dropdown.composeapp.generated.resources.china
import kmp_searchable_dropdown.composeapp.generated.resources.colombia
import kmp_searchable_dropdown.composeapp.generated.resources.costa_rica
import kmp_searchable_dropdown.composeapp.generated.resources.croatia
import kmp_searchable_dropdown.composeapp.generated.resources.cuba
import kmp_searchable_dropdown.composeapp.generated.resources.cyprus
import kmp_searchable_dropdown.composeapp.generated.resources.czech_republic
import kmp_searchable_dropdown.composeapp.generated.resources.denmark
import kmp_searchable_dropdown.composeapp.generated.resources.djibouti
import kmp_searchable_dropdown.composeapp.generated.resources.dominica
import kmp_searchable_dropdown.composeapp.generated.resources.dominican_republic
import kmp_searchable_dropdown.composeapp.generated.resources.ecuador
import kmp_searchable_dropdown.composeapp.generated.resources.egypt
import kmp_searchable_dropdown.composeapp.generated.resources.el_salvador
import kmp_searchable_dropdown.composeapp.generated.resources.equatorial_guinea
import kmp_searchable_dropdown.composeapp.generated.resources.estonia
import kmp_searchable_dropdown.composeapp.generated.resources.ethiopia
import kmp_searchable_dropdown.composeapp.generated.resources.fiji
import kmp_searchable_dropdown.composeapp.generated.resources.finland
import kmp_searchable_dropdown.composeapp.generated.resources.france
import kmp_searchable_dropdown.composeapp.generated.resources.gabon
import kmp_searchable_dropdown.composeapp.generated.resources.gambia
import kmp_searchable_dropdown.composeapp.generated.resources.germany
import kmp_searchable_dropdown.composeapp.generated.resources.ghana
import kmp_searchable_dropdown.composeapp.generated.resources.greece
import kmp_searchable_dropdown.composeapp.generated.resources.grenada
import kmp_searchable_dropdown.composeapp.generated.resources.guatemala
import kmp_searchable_dropdown.composeapp.generated.resources.guyana
import kmp_searchable_dropdown.composeapp.generated.resources.haiti
import kmp_searchable_dropdown.composeapp.generated.resources.honduras
import kmp_searchable_dropdown.composeapp.generated.resources.hungary
import kmp_searchable_dropdown.composeapp.generated.resources.iceland
import kmp_searchable_dropdown.composeapp.generated.resources.india
import kmp_searchable_dropdown.composeapp.generated.resources.indonesia
import kmp_searchable_dropdown.composeapp.generated.resources.iran
import kmp_searchable_dropdown.composeapp.generated.resources.iraq
import kmp_searchable_dropdown.composeapp.generated.resources.ireland
import kmp_searchable_dropdown.composeapp.generated.resources.italy
import kmp_searchable_dropdown.composeapp.generated.resources.jamaica
import kmp_searchable_dropdown.composeapp.generated.resources.japan
import kmp_searchable_dropdown.composeapp.generated.resources.jordan
import kmp_searchable_dropdown.composeapp.generated.resources.kazakhstan
import kmp_searchable_dropdown.composeapp.generated.resources.kenya
import kmp_searchable_dropdown.composeapp.generated.resources.kiribati
import kmp_searchable_dropdown.composeapp.generated.resources.kuwait
import kmp_searchable_dropdown.composeapp.generated.resources.kyrgyzstan
import org.jetbrains.compose.resources.DrawableResource

data class Country(
    val name: String,
    val flagResources: DrawableResource,
    val phoneCode: String
)



val countries = listOf(
    Country("Afghanistan", Res.drawable.afghanistan, "+93"),
    Country("Albania", Res.drawable.albania, "+355"),
    Country("Brazil", Res.drawable.brazil, "+55"),
    Country("Brunei", Res.drawable.brunei, "+673"),
    Country("Bulgaria", Res.drawable.bulgaria, "+359"),
    Country("Burkina Faso", Res.drawable.burkina_faso, "+226"),
    Country("Burundi", Res.drawable.burundi, "+257"),
    Country("Cambodia", Res.drawable.cambodia, "+855"),
        Country("Cameroon", Res.drawable.cameroon, "+237"),
    Country("Canada", Res.drawable.canada, "+1"),
    Country("Chile", Res.drawable.chile, "+56"),
    Country("China", Res.drawable.china, "+86"),
    Country("Colombia", Res.drawable.colombia, "+57"),
    Country("Costa Rica", Res.drawable.costa_rica, "+506"),
    Country("Croatia", Res.drawable.croatia, "+385"),
    Country("Cuba", Res.drawable.cuba, "+53"),
    Country("Cyprus", Res.drawable.cyprus, "+357"),
    Country("Czech Republic", Res.drawable.czech_republic, "+420"),
    Country("Denmark", Res.drawable.denmark, "+45"),
    Country("Djibouti", Res.drawable.djibouti, "+253"),
    Country("Dominica", Res.drawable.dominica, "+1767"),
    Country("Dominican Republic", Res.drawable.dominican_republic, "+1"),
    Country("Ecuador", Res.drawable.ecuador, "+593"),
    Country("Egypt", Res.drawable.egypt, "+20"),
    Country("El Salvador", Res.drawable.el_salvador, "+503"),
    Country("Equatorial Guinea", Res.drawable.equatorial_guinea, "+240"),
    Country("Estonia", Res.drawable.estonia, "+372"),
    Country("Ethiopia", Res.drawable.ethiopia, "+251"),
    Country("Fiji", Res.drawable.fiji, "+679"),
    Country("Finland", Res.drawable.finland, "+358"),
    Country("France", Res.drawable.france, "+33"),
    Country("Gabon", Res.drawable.gabon, "+241"),
    Country("Gambia", Res.drawable.gambia, "+220"),
    Country("Germany", Res.drawable.germany, "+49"),
    Country("Ghana", Res.drawable.ghana, "+233"),
    Country("Greece", Res.drawable.greece, "+30"),
    Country("Grenada", Res.drawable.grenada, "+1473"),
    Country("Guatemala", Res.drawable.guatemala, "+502"),
    Country("Guyana", Res.drawable.guyana, "+592"),
    Country("Haiti", Res.drawable.haiti, "+509"),
    Country("Honduras", Res.drawable.honduras, "+504"),
    Country("Hungary", Res.drawable.hungary, "+36"),
    Country("Iceland", Res.drawable.iceland, "+354"),
    Country("India", Res.drawable.india, "+91"),
    Country("Indonesia", Res.drawable.indonesia, "+62"),
    Country("Iran", Res.drawable.iran, "+98"),
    Country("Iraq", Res.drawable.iraq, "+964"),
    Country("Ireland", Res.drawable.ireland, "+353"),
    Country("Italy", Res.drawable.italy, "+39"),
    Country("Jamaica", Res.drawable.jamaica, "+1876"),
    Country("Japan", Res.drawable.japan, "+81"),
    Country("Jordan", Res.drawable.jordan, "+962"),
    Country("Kazakhstan", Res.drawable.kazakhstan, "+7"),
    Country("Kenya", Res.drawable.kenya, "+254"),
    Country("Kiribati", Res.drawable.kiribati, "+686"),
    Country("Kuwait", Res.drawable.kuwait, "+965"),
    Country("Kyrgyzstan", Res.drawable.kyrgyzstan, "+996")
)
