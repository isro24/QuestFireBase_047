package com.example.praktikumfirebase.ui.home.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikumfirebase.model.Mahasiswa
import com.example.praktikumfirebase.ui.PenyediaViewModel
import com.example.praktikumfirebase.ui.home.viewmodel.HomeViewModel
import com.example.praktikumfirebase.ui.home.viewmodel.HomeuiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedMahasiswa: Mahasiswa? by remember { mutableStateOf(null) }

    if (showDeleteDialog && selectedMahasiswa != null) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                selectedMahasiswa?.let { mahasiswa ->
                    viewModel.deleteMhs(mahasiswa)
                }
                showDeleteDialog = false
            },
            onDeleteCancel = {
                showDeleteDialog = false
            }
        )
    }

    Scaffold (
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            )
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Mahasiswa" )
            }
        },
    ){innerPadding ->
        HomeStatus(
            homeUiState = viewModel.mhsUiState,
            retryAction = {viewModel.getMhs()}, modifier = Modifier.padding(innerPadding),
            onDetailClick = {
                viewModel.getMhs()
            },
            onDeleteClick = {
                viewModel.deleteMhs(it)
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeuiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Mahasiswa) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf<Mahasiswa?>(null) }
    when (homeUiState){
        is HomeuiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeuiState.Success ->
            MhsLayout(
                mahasiswa = homeUiState.data, modifier = modifier,
                onDetailClick = {
                    onDeleteClick(it)
                },onDeleteClick = {
                    onDeleteClick(it)
                }
            )
        is HomeuiState.Error -> OnError(retryAction,
            modifier = modifier.fillMaxSize(), message = homeUiState.e.message?:"Error")    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Text(text = "Loading.....")
}

@Composable
fun OnError(
    retryAction: () -> Unit, 
    modifier: Modifier,
    message: String)
{
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Terjadi Kesalahan : $message",
            modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text( "Retry")
        }
    }
}

@Composable
fun MhsLayout(
    mahasiswa: List<Mahasiswa>,
    modifier: Modifier= Modifier,
    onDetailClick: (Mahasiswa) -> Unit,
    onDeleteClick: (Mahasiswa) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items (
            items = mahasiswa,
            itemContent = { mhs ->
                MhsCard(
                    mahasiswa = mhs,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDetailClick(mhs) },
                    onDeleteClick = {
                        onDeleteClick(mhs)
                    }
                )
            }
        )
    }
}

@Composable
fun MhsCard(
    mahasiswa: Mahasiswa,
    modifier: Modifier = Modifier,
    onDeleteClick: (Mahasiswa) -> Unit = {}
){
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    mahasiswa.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(mahasiswa) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = mahasiswa.nim,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = mahasiswa.kelas,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = mahasiswa.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = {/*Do Nothing*/},
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data ini?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Yes")
            }
        }
    )
}