package com.example.tb.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tb.R
import com.example.tb.ui.theme.Routes
import com.example.tb.ui.theme.abu
import com.example.tb.ui.theme.biru1
import com.example.tb.ui.theme.biru2
import com.example.tb.ui.theme.putih
import com.example.tb.ui.theme.ungu2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navController: NavHostController = rememberNavController()
) {
    val image = painterResource(id = R.drawable.logoheader)

    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Spacer(modifier = Modifier.height(76.dp))
        Box(){
            Image(painter = image, contentDescription = null)
        }
        Spacer(modifier = Modifier.height(182.dp))
        Box(modifier = Modifier
            .width(350.dp)
            .height(280.dp)
            .background(abu, shape = RoundedCornerShape(8.dp))
            .border(
                width = 4.dp,
                color = biru2,
                shape = RoundedCornerShape(8.dp)
            )
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Login",
                    color = biru2,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))

                var email by remember { mutableStateOf("") }
                var isEmailValid by remember { mutableStateOf(true) }
                OutlinedTextField(
                    label = { Text(text = "Email") },
                    value = email,
                    onValueChange =
                    {
                        email = it
                        isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    },
                    isError = !isEmailValid,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = if (isEmailValid) ungu2 else Color.Red,
                        errorBorderColor = Color.Red
                    )
                )
                if (!isEmailValid) {
                    Text(
                        text = "Invalid email format",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                var password by remember { mutableStateOf("") }
                var isPasswordVisible by remember { mutableStateOf(false) }
                OutlinedTextField(
                    label = { Text(text = "Password") },
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {isPasswordVisible = !isPasswordVisible})
                        {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = ungu2,
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Don't have an account? ",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = biru1,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.Register)
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(183.dp))

        Button(
            modifier = Modifier
                .width(250.dp)
                .height(55.dp)
            ,
            onClick = {navController.navigate(Routes.Main)},
            colors = ButtonDefaults.buttonColors(
                containerColor  = biru1
            )
        ) {
            Text(
                "Login",
                color = putih,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold

            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPagePreview() {
    LoginPage()
}