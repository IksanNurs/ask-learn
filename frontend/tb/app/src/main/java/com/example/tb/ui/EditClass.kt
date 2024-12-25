package com.example.tb.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tb.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditClass(
    navController: NavHostController
) {
    var subject by remember { mutableStateOf("Mobile Programming") }
    var topic by remember { mutableStateOf("UI Development") }
    var datetutor by remember { mutableStateOf("2023-12-20") }
    var timetutor by remember { mutableStateOf("14:00") }
    var locationtutor by remember { mutableStateOf("Room 301") }
    var kuota by remember { mutableStateOf("20") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Class", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF381224)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Edit Form Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEBEAEE))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        CustomTextField(
                            value = subject,
                            onValueChange = { subject = it },
                            label = "Subject"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomTextField(
                            value = topic,
                            onValueChange = { topic = it },
                            label = "Topic"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CustomTextField(
                                value = datetutor,
                                onValueChange = { datetutor = it },
                                label = "Date",
                                modifier = Modifier.weight(1f)
                            )
                            CustomTextField(
                                value = timetutor,
                                onValueChange = { timetutor = it },
                                label = "Time",
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomTextField(
                            value = locationtutor,
                            onValueChange = { locationtutor = it },
                            label = "Location"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomTextField(
                            value = kuota,
                            onValueChange = { kuota = it },
                            label = "Max Participants"
                        )
                    }
                }
            }

            item {
                // Participants Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEBEAEE))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Participants",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2F2C4F)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        ParticipantsList()
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        border = BorderStroke(1.dp, Color(0xFF2F2C4F))
                    ) {
                        Text("Cancel", color = Color(0xFF2F2C4F))
                    }
                    Button(
                        onClick = { /* Handle save */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D2B4F))
                    ) {
                        Text("Save Changes")
                    }
                }
            }
        }
    }
}

@Composable
private fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
private fun ParticipantsList() {
    val participants = listOf(
        "John Doe",
        "Jane Smith",
        "Mike Johnson"
    )

    Column {
        participants.forEach { name ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF2F2C4F)
                    )
                    IconButton(
                        onClick = { /* Handle remove participant */ },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Remove",
                            tint = Color(0xFF6D2B4F)
                        )
                    }
                }
            }
        }
    }
}

