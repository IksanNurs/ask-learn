package com.example.tb.navigation

import androidx.compose.runtime.Composable  // Pastikan impor ini ada
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tb.pages.ChangePasswordScreen
import com.example.tb.pages.FormAddPost
import com.example.tb.ui.EditProfileScreen
import com.example.tb.pages.LandingPage
import com.example.tb.pages.MainScreen
import com.example.tb.ui.ProfileScreen

import com.example.tb.ui.AvailableTutor
import com.example.tb.ui.ClassDetail
import com.example.tb.ui.ClassHistory
import com.example.tb.ui.CreateClass
import com.example.tb.ui.EditClass
import com.example.tb.ui.EditUpcomingClass
import com.example.tb.ui.ListClassScreen
import com.example.tb.ui.Login
import com.example.tb.ui.MyClassScreen
import com.example.tb.ui.Register
import com.example.tb.ui.UpcomingClass
import com.example.tb.ui.ViewClassScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "landing", modifier = modifier) {
        composable("landing") { LandingPage(navController) }
        composable("login") { Login(navController) }
        composable("register") { Register(navController) }
        composable("main") { MainScreen(navController) }
        composable("availableTutor") { AvailableTutor(navController) }
        composable("classDetail") { ClassDetail() }
        composable("classHistory") { ClassHistory() }
        composable("createClass") { CreateClass() }
        composable("editUpcomingClass") { EditUpcomingClass() }
        composable("upcomingClass") { UpcomingClass() }
        composable("profile") { ProfileScreen(navController) }
        composable("editProfile") { EditProfileScreen(navController) }
        composable("changePassword") { ChangePasswordScreen(navController) }
        composable("myClass") { MyClassScreen(navController) }
 composable(
    route = "add_post?id={id}&description={description}&image={image}",
    arguments = listOf(
        navArgument("id") { type = NavType.IntType; defaultValue = -1 },
        navArgument("description") { type = NavType.StringType; defaultValue = "" },
        navArgument("image") { type = NavType.StringType; defaultValue = "" }
    )
) { backStackEntry ->
    FormAddPost(
        itemId = backStackEntry.arguments?.getInt("id") ?: -1,
        initialDescription = backStackEntry.arguments?.getString("description") ?: "",
        initialImage = backStackEntry.arguments?.getString("image") ?: "",
        onBack = { navController.navigateUp() }
    )
}


        composable("edit_class_screen") {
            EditClass(navController)
        }
           composable(
        route = "list_class_screen/{tutorName}/{department}",
        arguments = listOf(
            navArgument("tutorName") { type = NavType.StringType },
            navArgument("department") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        ListClassScreen(
            navController = navController,
            tutorName = backStackEntry.arguments?.getString("tutorName") ?: "",
            department = backStackEntry.arguments?.getString("department") ?: ""
        )
    }
        composable(
            route = "view_class_screen/{className}/{subject}",
            arguments = listOf(
                navArgument("className") { type = NavType.StringType },
                navArgument("subject") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ViewClassScreen(
                navController = navController,
                className = backStackEntry.arguments?.getString("className") ?: "",
                subject = backStackEntry.arguments?.getString("subject") ?: ""
            )
        }
    }
}