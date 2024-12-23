package com.example.tb.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tb.R
import com.example.tb.ui.theme.abu2
import com.example.tb.ui.theme.biru1
import com.example.tb.ui.theme.putih
import com.example.tb.ui.theme.ungu2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAddPost (
    modifier: Modifier = Modifier,
    onBack: () -> Unit
){
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(
                    "Add Post",
                    color = putih
                    )
                },
               navigationIcon = {
                   IconButton(onClick = { onBack() }) {
                       Icon(
                           painter = painterResource(id = R.drawable.ic_back),
                           contentDescription = null,
                           tint = putih
                       )
                   }
               },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = ungu2,
                    titleContentColor = putih,
                    navigationIconContentColor = putih,
                )
            )
        }
    ){ innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var post by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(
                    text = "Add text here",
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )},
                value = post,
                onValueChange = {post = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = abu2,
                    focusedBorderColor = ungu2
                ),
                maxLines = 10,
                singleLine = false
            )
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(
                        color = abu2,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_upload),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier
                    .width(250.dp)
                    .height(55.dp)
                ,
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor  = biru1
                )
            ) {
                Text(
                    "Post",
                    color = putih,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )
            }
        }
    }
}