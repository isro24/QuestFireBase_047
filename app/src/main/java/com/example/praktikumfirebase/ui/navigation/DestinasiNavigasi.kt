package com.example.praktikumfirebase.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHome : DestinasiNavigasi {
    override val route: String =  "home"
    override val titleRes: String =  "Home"
}

object DestinasiInsert : DestinasiNavigasi {
    override val route: String =  "insert"
    override val titleRes: String =  "Insert"
}

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail "
    const val NIM = "nim"
    val routeWithArgs = "$route/{$NIM}"
}