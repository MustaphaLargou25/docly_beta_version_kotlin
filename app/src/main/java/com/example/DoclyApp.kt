package com.example

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.TextSecondaryLight
import com.example.ui.theme.SurfaceLight

enum class AppRoute {
    ONBOARDING, SIGN_IN, HOME, LIBRARY, COMMUNITY, PROFILE
}

@Composable
fun DoclyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppRoute.ONBOARDING.name

    val showBottomNav = currentRoute in listOf(
        AppRoute.HOME.name,
        AppRoute.LIBRARY.name,
        AppRoute.COMMUNITY.name,
        AppRoute.PROFILE.name
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (showBottomNav) {
                DoclyBottomNavigation(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.ONBOARDING.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoute.ONBOARDING.name) {
                OnboardingScreen(onFinish = {
                    navController.navigate(AppRoute.SIGN_IN.name) {
                        popUpTo(AppRoute.ONBOARDING.name) { inclusive = true }
                    }
                })
            }
            composable(AppRoute.SIGN_IN.name) {
                SignInScreen(onSignIn = {
                    navController.navigate(AppRoute.HOME.name) {
                        popUpTo(AppRoute.SIGN_IN.name) { inclusive = true }
                    }
                })
            }
            composable(AppRoute.HOME.name) {
                HomeScreen()
            }
            composable(AppRoute.LIBRARY.name) {
                LibraryScreen()
            }
            composable(AppRoute.COMMUNITY.name) {
                CommunityScreen()
            }
            composable(AppRoute.PROFILE.name) {
                ProfileScreen()
            }
        }
    }
}

data class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: String
)

val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(AppRoute.HOME.name, Icons.Filled.Home, Icons.Filled.Home, "Home"),
    TopLevelDestination(AppRoute.LIBRARY.name, Icons.Filled.MenuBook, Icons.Filled.MenuBook, "Library"),
    TopLevelDestination(AppRoute.COMMUNITY.name, Icons.Filled.People, Icons.Filled.People, "Community"),
    TopLevelDestination(AppRoute.PROFILE.name, Icons.Filled.Person, Icons.Filled.Person, "Profile")
)

@Composable
fun DoclyBottomNavigation(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.outline),
        tonalElevation = 0.dp
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { destination ->
            val selected = currentRoute == destination.route
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(destination.route) },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = destination.iconTextId
                    )
                },
                label = { Text(destination.iconTextId, fontWeight = if (selected) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Medium, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = TextSecondaryLight,
                    unselectedTextColor = TextSecondaryLight
                )
            )
        }
    }
}
