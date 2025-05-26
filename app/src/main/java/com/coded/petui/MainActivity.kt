package com.coded.petui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coded.petui.ui.theme.PetUITheme
import com.coded.petui.viewmodel.PetViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetUITheme {
                val viewModel: PetViewModel = viewModel()
                PetListScreen(viewModel = viewModel)
            }
        }
    }
}
