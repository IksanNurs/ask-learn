package com.example.tb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.tb.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun AvailableTutor(
    navController: NavHostController = rememberNavController()
) {
    Scaffold (
        topBar = {
            TopLayoutTutor()
        },
        content = { paddingValues ->
            Box (
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(color = Color.White)
            ){
                Column {
                    MenuBarTutor()
                    Spacer(modifier = Modifier.padding(vertical = 5.dp))
                    LazyColumn {
                        item{
                            TutorList()
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomLayoutTutor()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLayoutTutor(){
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

@Composable
fun MenuBarTutor(){
    Box (
        modifier = Modifier.background(color = Color.White)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Available Tutor",
                    color = Color(0xFF6D2B4F),
                    fontWeight = FontWeight(600)
                )
            }
            Column (
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Create Class",
                    color = Color.Black,
                    fontWeight = FontWeight(600)
                )
            }
        }
    }
}


@Composable
fun TutorList(){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEBEAEE)
        )
    ){
        Row (
            modifier = Modifier
                .padding(15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Column {
                Image(
                    painter = painterResource(id = R.drawable.jisoo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(100))
                )
            }
            Column (
                modifier = Modifier
                    .padding(start = 20.dp)
            ){
                Column {
                    Text(
                        text = "Kim Jisoo",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2F2C4F)
                    )
                    Text(
                        text = "Information System",
                        color = Color(0xFF2F2C4F)
                    )
                }
            }
        }

    }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEBEAEE)
        )
    ){
        Row (
            modifier = Modifier
                .padding(15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Column {
                Image(
                    painter = painterResource(id = R.drawable.jisoo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(100))
                )
            }
            Column (
                modifier = Modifier
                    .padding(start = 20.dp)
            ){
                Column {
                    Text(
                        text = "Kim Jisoo",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2F2C4F)
                    )
                    Text(
                        text = "Information System",
                        color = Color(0xFF2F2C4F)
                    )
                }
            }
        }

    }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEBEAEE)
        )
    ){
        Row (
            modifier = Modifier
                .padding(15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Column {
                Image(
                    painter = painterResource(id = R.drawable.jisoo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(100))
                )
            }
            Column (
                modifier = Modifier
                    .padding(start = 20.dp)
            ){
                Column {
                    Text(
                        text = "Kim Jisoo",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2F2C4F)
                    )
                    Text(
                        text = "Information System",
                        color = Color(0xFF2F2C4F)
                    )
                }
            }
        }

    }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEBEAEE)
        )
    ){
        Row (
            modifier = Modifier
                .padding(15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Column {
                Image(
                    painter = painterResource(id = R.drawable.jisoo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(100))
                )
            }
            Column (
                modifier = Modifier
                    .padding(start = 20.dp)
            ){
                Column {
                    Text(
                        text = "Kim Jisoo",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2F2C4F)
                    )
                    Text(
                        text = "Information System",
                        color = Color(0xFF2F2C4F)
                    )
                }
            }
        }

    }
}

@Composable
fun BottomLayoutTutor(){
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
fun AvailableTutorPreview(){
    AvailableTutor()
}

