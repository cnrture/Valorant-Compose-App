package com.canerture.valorantcompose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.canerture.valorantcompose.presentation.agent.agentdetail.AgentDetailScreen
import com.canerture.valorantcompose.presentation.agent.agents.AgentsScreen
import com.canerture.valorantcompose.presentation.competitivetiers.CompetitiveTiersScreen
import com.canerture.valorantcompose.presentation.main.MainScreen
import com.canerture.valorantcompose.presentation.map.mapdetail.MapDetailScreen
import com.canerture.valorantcompose.presentation.map.maps.MapsScreen
import com.canerture.valorantcompose.presentation.splash.SplashScreen
import com.canerture.valorantcompose.presentation.weapon.weapondetail.WeaponDetailScreen
import com.canerture.valorantcompose.presentation.weapon.weapons.WeaponsScreen

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(
                navigateToAgents = {
                    navController.navigate(Screen.Agents.route)
                }
            )
        }

        composable(route = Screen.Main.route) {
            MainScreen(
                navigateToRouteOne = {
                    navController.navigate(route = it)
                },
                navigateToRouteTwo = {
                    navController.navigate(it)
                }
            )
        }

        composable(route = Screen.Agents.route) {
            AgentsScreen(
                navigateToAgentDetail = {
                    navController.navigate("${Screen.AgentDetail.route}/$it")
                }
            )
        }

        composable(route = "${Screen.AgentDetail.route}/{agentUuid}",
            arguments = listOf(
                navArgument("agentUuid") { type = NavType.StringType }
            )
        ) {
            it.arguments?.getString("agentUuid")?.let { agentUuid ->
                AgentDetailScreen(agentUuid)
            }
        }

        composable(route = Screen.Maps.route) {
            MapsScreen(
                navigateToMapDetail = {
                    navController.navigate("${Screen.MapDetail.route}/$it")
                }
            )
        }

        composable(route = "${Screen.MapDetail.route}/{mapUuid}",
            arguments = listOf(
                navArgument("mapUuid") { type = NavType.StringType }
            )) {
            it.arguments?.getString("mapUuid")?.let { mapUuid ->
                MapDetailScreen(mapUuid)
            }
        }

        composable(route = Screen.Weapons.route) {
            WeaponsScreen(
                navigateToWeaponDetail = {
                    navController.navigate("${Screen.WeaponDetail.route}/$it")
                }
            )
        }

        composable(route = "${Screen.WeaponDetail.route}/{weaponUuid}",
            arguments = listOf(
                navArgument("weaponUuid") { type = NavType.StringType }
            )) {
            it.arguments?.getString("weaponUuid")?.let { weaponUuid ->
                WeaponDetailScreen(weaponUuid)
            }
        }

        composable(route = Screen.CompetitiveTiers.route) {
            CompetitiveTiersScreen()
        }
    }
}