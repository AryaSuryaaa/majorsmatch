package com.anugrah.majorsmatch.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItemScreen(val route: String, val icon: ImageVector, val title: String) {

    data object Home : BottomNavItemScreen("home_screen", Icons.Default.Home, "Home")

    data object Explore : BottomNavItemScreen("explore_screen", Icons.Default.Search, "Explore")

    data object Profile : BottomNavItemScreen("profile_screen", Icons.Default.Person, "Profile")

}
