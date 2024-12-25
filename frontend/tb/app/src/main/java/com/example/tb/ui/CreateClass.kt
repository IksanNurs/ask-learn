package com.example.tb.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.tb.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClass(
    navController: NavHostController = rememberNavController()
) {
    Scaffold (

        content = { paddingValues ->
            Box (
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(color = Color.White)
            ){
                Column {
                    LazyColumn {
                        item{
                            FormTutor()
                        }
                    }
                }
            }
        },

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLayout(){
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF381224)
        ),
        actions = {
            Image(
                painter = painterResource(id = R.drawable.jadwal),
                modifier = Modifier
                    .size(22.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
    )
}


@ExperimentalMaterial3Api
@Composable
fun FormTutor(){
    var subject by remember { mutableStateOf("") }
    var topic by remember { mutableStateOf("") }
    var datetutor by remember { mutableStateOf("") }
    var timetutor by remember { mutableStateOf("") }
    var locationtutor by remember { mutableStateOf("") }
    var uploadkhs by remember { mutableStateOf("") }
    var kuota by remember { mutableStateOf("") }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(0xFFEBEAEE))
    ){
        Column {
            Row {
                TextField(
                    value = subject,
                    onValueChange = { subject = it },
                    placeholder = {
                        Text(
                            text = "Subject",
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.White
                    )
                )
            }
            Row {
                TextField(
                    value = topic,
                    onValueChange = { topic = it },
                    placeholder = {
                        Text(
                            text = "Topic",
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.White
                    )
                )
            }
            Row {
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    TextField(
                        value = datetutor,
                        onValueChange = { datetutor = it },
                        placeholder = {
                            Text(
                                text = "Date ",
                                fontSize = 13.sp
                            )
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            containerColor = Color.White
                        )
                    )
                }
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    TextField(
                        value = timetutor,
                        onValueChange = { timetutor = it },
                        placeholder = {
                            Text(
                                text = "Time",
                                fontSize = 13.sp
                            )
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            containerColor = Color.White
                        )
                    )
                }
            }
            Row {
                TextField(
                    value = locationtutor,
                    onValueChange = { locationtutor = it },
                    placeholder = {
                        Text(
                            text = "Location",
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.White
                    )
                )
            }
            Row {
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    TextField(
                        value = kuota,
                        onValueChange = { kuota = it },
                        placeholder = {
                            Text(
                                text = "Max kuota",
                                fontSize = 13.sp
                            )
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            containerColor = Color.White
                        )
                    )
                }
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    TextField(
                        value = uploadkhs,
                        onValueChange = { uploadkhs = it },
                        placeholder = {
                            Text(
                                text = "Upload KHS",
                                fontSize = 13.sp
                            )
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            containerColor = Color.White
                        )
                    )
                }
            }
            Row {
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        contentPadding = PaddingValues(vertical = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.dp, color = Color(0xFF2F2C4F))
                    ) {
                        Text(
                            text = "Reset",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color= Color(0xFF2F2C4F)
                        )
                    }
                }
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2F2C4F)
                        ),
                        contentPadding = PaddingValues(vertical = 10.dp)
                    ) {
                        Text(
                            text = "Create Class",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BottomLayout(){
    Box(modifier = Modifier.fillMaxWidth()){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(6f),
            horizontalArrangement = Arrangement.Center
        ){
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(50.dp)
                    .zIndex(10f)
                    .offset(y = (-20).dp)
                    .align(Alignment.CenterVertically),
                containerColor = Color(0xFF6D2B4F),
                shape = RoundedCornerShape(100.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
        BottomAppBar (
            containerColor = Color(0xFF381224),
            modifier = Modifier
                .height(70.dp)
                .zIndex(5F)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .zIndex(8F),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                ){
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "home",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                ){
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.tutor),
                            contentDescription = "tutor",
                            modifier = Modifier.size(24.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                ){
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.list),
                            contentDescription = "list",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                ){
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.profil),
                            contentDescription = "profil",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground =true, showSystemUi = true)
@Composable
fun CreateClassPreview(){
    CreateClass()
}

