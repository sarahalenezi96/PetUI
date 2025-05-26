package com.coded.petui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.coded.petui.viewmodel.PetViewModel
import com.coded.petui.ui.theme.*
import com.coded.petui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetListScreen(viewModel: PetViewModel) {
    val pets by viewModel.petList
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp, bottom = 30.dp)
                    ) {
                        Text(
                            text = "Pet Adoption Center 🐾",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MutedOrange)
                .padding(padding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                error != null -> {
                    Text(
                        text = "Error: $error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(pets) { pet ->
                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    val imageUrl = pet.image ?: ""

                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(imageUrl)
                                            .error(R.drawable.default_pet)
                                            .fallback(R.drawable.default_pet)
                                            .build(),
                                        contentDescription = pet.name ?: "Pet Image",
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .padding(end = 16.dp)
                                    )

                                    Column {
                                        Text(
                                            text = pet.name ?: "Unnamed Pet",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = SoftGreenAccent
                                        )
                                        Text(
                                            text = "Age: ${pet.age ?: "?"}",
                                            color = PawPrintGray
                                        )
                                        Text(
                                            text = "Gender: ${pet.gender ?: "Unknown"}",
                                            color = PawPrintGray
                                        )
                                        Text(
                                            text = if (pet.adopted) "Adopted" else "Available",
                                            color = if (pet.adopted) MutedOrange else SoftGreenAccent,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}