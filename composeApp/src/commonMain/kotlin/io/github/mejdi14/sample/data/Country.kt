package io.github.mejdi14.sample.data

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
    val phoneCode: String,
    val iso: String,
)

val countries = listOf(
    Country("Afghanistan", Res.drawable.afghanistan, "+93", "AF"),
    Country("Albania", Res.drawable.albania, "+355", "AL"),
    Country("Brazil", Res.drawable.brazil, "+55", "BR"),
    Country("Brunei", Res.drawable.brunei, "+673", "BN"),
    Country("Bulgaria", Res.drawable.bulgaria, "+359", "BG"),
    Country("Burkina Faso", Res.drawable.burkina_faso, "+226", "BF"),
    Country("Burundi", Res.drawable.burundi, "+257", "BI"),
    Country("Cambodia", Res.drawable.cambodia, "+855", "KH"),
    Country("Cameroon", Res.drawable.cameroon, "+237", "CM"),
    Country("Canada", Res.drawable.canada, "+1", "CA"),
    Country("Chile", Res.drawable.chile, "+56", "CL"),
    Country("China", Res.drawable.china, "+86", "CN"),
    Country("Colombia", Res.drawable.colombia, "+57", "CO"),
    Country("Costa Rica", Res.drawable.costa_rica, "+506", "CR"),
    Country("Croatia", Res.drawable.croatia, "+385", "HR"),
    Country("Cuba", Res.drawable.cuba, "+53", "CU"),
    Country("Cyprus", Res.drawable.cyprus, "+357", "CY"),
    Country("Czech Republic", Res.drawable.czech_republic, "+420", "CZ"),
    Country("Denmark", Res.drawable.denmark, "+45", "DK"),
    Country("Djibouti", Res.drawable.djibouti, "+253", "DJ"),
    Country("Dominica", Res.drawable.dominica, "+1767", "DM"),
    Country("Dominican Republic", Res.drawable.dominican_republic, "+1", "DO"),
    Country("Ecuador", Res.drawable.ecuador, "+593", "EC"),
    Country("Egypt", Res.drawable.egypt, "+20", "EG"),
    Country("El Salvador", Res.drawable.el_salvador, "+503", "SV"),
    Country("Equatorial Guinea", Res.drawable.equatorial_guinea, "+240", "GQ"),
    Country("Estonia", Res.drawable.estonia, "+372", "EE"),
    Country("Ethiopia", Res.drawable.ethiopia, "+251", "ET"),
    Country("Fiji", Res.drawable.fiji, "+679", "FJ"),
    Country("Finland", Res.drawable.finland, "+358", "FI"),
    Country("France", Res.drawable.france, "+33", "FR"),
    Country("Gabon", Res.drawable.gabon, "+241", "GA"),
    Country("Gambia", Res.drawable.gambia, "+220", "GM"),
    Country("Germany", Res.drawable.germany, "+49", "DE"),
    Country("Ghana", Res.drawable.ghana, "+233", "GH"),
    Country("Greece", Res.drawable.greece, "+30", "GR"),
    Country("Grenada", Res.drawable.grenada, "+1473", "GD"),
    Country("Guatemala", Res.drawable.guatemala, "+502", "GT"),
    Country("Guyana", Res.drawable.guyana, "+592", "GY"),
    Country("Haiti", Res.drawable.haiti, "+509", "HT"),
    Country("Honduras", Res.drawable.honduras, "+504", "HN"),
    Country("Hungary", Res.drawable.hungary, "+36", "HU"),
    Country("Iceland", Res.drawable.iceland, "+354", "IS"),
    Country("India", Res.drawable.india, "+91", "IN"),
    Country("Indonesia", Res.drawable.indonesia, "+62", "ID"),
    Country("Iran", Res.drawable.iran, "+98", "IR"),
    Country("Iraq", Res.drawable.iraq, "+964", "IQ"),
    Country("Ireland", Res.drawable.ireland, "+353", "IE"),
    Country("Italy", Res.drawable.italy, "+39", "IT"),
    Country("Jamaica", Res.drawable.jamaica, "+1876", "JM"),
    Country("Japan", Res.drawable.japan, "+81", "JP"),
    Country("Jordan", Res.drawable.jordan, "+962", "JO"),
    Country("Kazakhstan", Res.drawable.kazakhstan, "+7", "KZ"),
    Country("Kenya", Res.drawable.kenya, "+254", "KE"),
    Country("Kiribati", Res.drawable.kiribati, "+686", "KI"),
    Country("Kuwait", Res.drawable.kuwait, "+965", "KW"),
    Country("Kyrgyzstan", Res.drawable.kyrgyzstan, "+996", "KG"),
)
