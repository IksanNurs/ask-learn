package com.example.tbsewaku.navigation

import androidx.compose.runtime.Composable  // Pastikan impor ini ada
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tb.pages.LoginPage
import com.example.tb.pages.MainScreen
import com.example.tb.pages.RegisterPage
import com.example.tb.ui.AvailableTutor
import com.example.tb.ui.ClassDetail
import com.example.tb.ui.ClassHistory
import com.example.tb.ui.CreateClass
import com.example.tb.ui.EditUpcomingClass
import com.example.tb.ui.UpcomingClass
import com.example.tb.ui.Welcome


sealed class Routes(val route: String) {
    object Welcome : Routes("welcome")
    object Login : Routes("login") 
    object Register : Routes("register")
    object Main : Routes("main")
    object AvailableTutor : Routes("availableTutor")
    object ClassDetail : Routes("classDetail")
    object ClassHistory : Routes("classHistory")
    object CreateClass : Routes("createClass")
    object EditUpcomingClass : Routes("editUpcomingClass")
    object UpcomingClass : Routes("upcomingClass")
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Welcome.route
    ) {
        composable(Routes.Welcome.route) {
            Welcome(navController)
        }
        
        composable(Routes.Login.route) {
            LoginPage(navController)
        }

        composable(Routes.Register.route) {
            RegisterPage(navController)
        }

        composable(Routes.Main.route) {
            MainScreen(navController)
        }

        composable(Routes.AvailableTutor.route) {
            AvailableTutor()
        }

        composable(Routes.ClassDetail.route) {
            ClassDetail()
        }

        composable(Routes.ClassHistory.route) {
            ClassHistory()
        }

        composable(Routes.CreateClass.route) {
            CreateClass()
        }

        composable(Routes.EditUpcomingClass.route) {
            EditUpcomingClass()
        }

        composable(Routes.UpcomingClass.route) {
            UpcomingClass()
        }
    }
}
