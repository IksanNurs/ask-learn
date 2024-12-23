package com.example.tb.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.example.tb.R
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.navigation.NavController
import com.example.tb.ui.theme.Routes
import com.example.tb.ui.theme.abu
import com.example.tb.ui.theme.biru1
import com.example.tb.ui.theme.putih
import com.example.tb.ui.theme.ungu1
import com.example.tb.ui.theme.ungu2
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(
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
        Spacer(modifier = Modifier.height(48.dp))
        Box(modifier = Modifier
            .width(350.dp)
            .height(550.dp)
            .background(abu, shape = RoundedCornerShape(8.dp))
            .border(
                width = 4.dp,
                color = ungu1,
                shape = RoundedCornerShape(8.dp)
            )
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Register",
                    color = ungu2,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))

                var name by remember { mutableStateOf("") }
                OutlinedTextField(
                    label = { Text(text = "Full Name") },
                    value = name,
                    onValueChange = {name = it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = ungu2,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))

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

                var nim by remember { mutableStateOf("") }
                OutlinedTextField(
                    label = { Text(text = "NIM") },
                    value = nim,
                    onValueChange = {nim = it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = ungu2,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))

                var expanded by remember { mutableStateOf(false) }
                var selectedDepartment by remember { mutableStateOf("") }
                val departments = listOf("Teknik Komputer", "Sistem Informasi", "Informatika")
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedDepartment,
                        onValueChange = { selectedDepartment = it },
                        label = { Text("Departemen") },
                        readOnly = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = ungu2
                        ),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        departments.forEach { department ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedDepartment = department
                                    expanded = false
                                },
                                text = { Text(department) }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))

                var password by remember { mutableStateOf("") }
                var isPasswordVisible by remember { mutableStateOf(false) }
                var isConfirmPasswordVisible by remember { mutableStateOf(false) }
                var passwordError by remember { mutableStateOf(false) }
                OutlinedTextField(
                    label = { Text(text = "Password") },
                    value = password,
                    onValueChange =
                    {
                        password = it
                        passwordError = password.length < 8
                    },
                    isError = passwordError,
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
                Spacer(modifier = Modifier.height(4.dp))

                var confirmPassword by remember { mutableStateOf("") }
                OutlinedTextField(
                    label = { Text(text = "Confirm Password") },
                    value = confirmPassword,
                    onValueChange = {confirmPassword = it},
                    visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                            Icon(
                                imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (isConfirmPasswordVisible) "Hide confirm password" else "Show confirm password"
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
                        text = "Already have an account? ",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = biru1,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.Login)
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(48.dp))

        Button(
            modifier = Modifier
                .width(250.dp)
                .height(55.dp)
            ,
            onClick = {navController.navigate(Routes.Main)},
            colors = ButtonDefaults.buttonColors(
                containerColor  = ungu1
            )
        ) {
            Text(
                "Register",
                color = putih,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold

            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPagePreview() {
    RegisterPage()
}