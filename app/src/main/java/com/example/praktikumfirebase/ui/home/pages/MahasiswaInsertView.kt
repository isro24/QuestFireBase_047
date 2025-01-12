package com.example.praktikumfirebase.ui.home.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikumfirebase.ui.PenyediaViewModel
import com.example.praktikumfirebase.ui.home.viewmodel.FormState
import com.example.praktikumfirebase.ui.home.viewmodel.InsertViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertMhsView (
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState //State utama untuk loading, success, error

    val uiEvent = viewModel.uiEvent //state untuk form dan validasi
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Obsservasi perubahan state untuk snackbar dan navigasi
    LaunchedEffect (uiState){
        when (uiState){
            is FormState.Success -> {
                println(
                    "InsertMhsView: uiState is  FormState.Success, navigate to home " + uiState.message
                )

                coroutineScope.launch {
                    snackbarHostState.showSnackbar(uiState.message)
                }
                delay(700)
                //navigasi langsung
                onNavigate()

                viewModel.resetSnackBarMessage()
            }
            is FormState.Error -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(uiState.message)
                }
            }
            else -> Unit
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHostState(hostState = snackbarHostState)},
        topBar = {
            TopAppBar(
                title = { Text("Tambah Mahasiswa")  },
                navigationIcon = {
                    Button(onClick = onBack) {
                        Text(text = "Back")
                    }
                }
            )
        }
    ){ padding ->
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            InsertBodyMhs(
                uiState = uiEvent,
                homeUiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    if ( viewModel.validateFields()){
                        viewModel.insertMhs()
                    }
                }
            )
        }
    }
}