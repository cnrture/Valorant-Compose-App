package com.canerture.valorantcompose.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Main : Screen("main_screen")
    object Agents : Screen("agents_screen")
    object AgentDetail : Screen("agent_detail_screen")
    object Maps : Screen("maps_screen")
    object MapDetail : Screen("map_detail_screen")
    object Weapons : Screen("weapons_screen")
    object WeaponDetail : Screen("weapon_detail_screen")
    object CompetitiveTiers : Screen("competitive_tiers_screen")
}