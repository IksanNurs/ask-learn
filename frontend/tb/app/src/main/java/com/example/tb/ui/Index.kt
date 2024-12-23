package com.example.tb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tb.R



@Composable
fun Welcome(navController: NavHostController = rememberNavController()) {
    Scaffold (
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .zIndex(1F)
            ) {
                Logo()
            }
        },
        bottomBar = {
            Regislogin()
        }
    )
}

@Composable
fun Logo(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 125.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.byti),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.slogan),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
            )
        }
    }
}

@Composable
fun Regislogin(){
    BottomAppBar (
        modifier = Modifier
            .zIndex(5F)
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        containerColor = Color(0xFF381224)
    ) {
        Column {
            Row(
                modifier = Modifier.padding(start = 50.dp, top = 10.dp)
            ) {
                Text(
                    text = "Welcome To",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " ByTi",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )
            }
            Row {
                Text(
                    text = "This app will help you as a FTI student to study and find tutor",
                    modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 10.dp),
                    fontWeight = FontWeight(400),
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 50.dp)
            ) {
                Column {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(300.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        contentPadding = PaddingValues(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Register",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2F2C4F)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(300.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        contentPadding = PaddingValues(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Login",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2F2C4F)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomePreview() {
    Welcome()
}
