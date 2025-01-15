//package com.example.praktikumfirebase.ui.detail.pages
//
//import DetailUiState
//import DetailViewModel
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.praktikumfirebase.model.Mahasiswa
//import com.example.praktikumfirebase.ui.PenyediaViewModel
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MahasiswaDetailView(
//    modifier: Modifier = Modifier,
//    NavigateBack: () -> Unit,
//    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
//) {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//
//    Scaffold(
//        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
////            TopAppBar(
////                title = DestinasiDetail.titleRes,
////                canNavigateBack = true,
////                scrollBehavior = scrollBehavior,
////                navigateUp = NavigateBack
////            )
//        },
//    ) { innerPadding ->
//        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
//
//        BodyDetailMhs(
//            detailUiState = viewModel.detailUiState,
//            modifier = Modifier.padding(innerPadding),
//            onDeleteClick = {
//                deleteConfirmationRequired = true
//            }
//        )
//    }
//}
//
//
//@Composable
//fun BodyDetailMhs(
//    modifier: Modifier = Modifier,
//    detailUiState: DetailUiState,
//    onDeleteClick: () -> Unit
//) {
//    when {
//        detailUiState.isLoading -> {
//            Box(
//                modifier = modifier.fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator()
//            }
//        }
//        detailUiState.isError -> {
//            Box(
//                modifier = modifier.fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = detailUiState.errorMessage,
//                    color = Color.Red
//                )
//            }
//        }
//        detailUiState.isUiEventNotEmpty -> {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                ItemDetailMhs(
//                    mahasiswa = detailUiState.detailUiEvent.toMhs(),
//                    modifier = modifier
//                )
//
//                Spacer(modifier = Modifier.padding(8.dp))
//                Button(
//                    onClick = onDeleteClick,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text(text = "Delete")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ItemDetailMhs(
//    modifier: Modifier = Modifier,
//    mahasiswa: Mahasiswa,
//){
//    Card(
//        modifier = modifier.fillMaxWidth().padding(top = 20.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
//            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
//        )
//    ){
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//        ) {
//            ComponentDetailMhs(judul = "NIM", isinya = mahasiswa.nim)
//            Spacer(modifier = Modifier.padding(4.dp))
//            ComponentDetailMhs(judul = "Nama", isinya = mahasiswa.nama)
//            Spacer(modifier = Modifier.padding(4.dp))
//            ComponentDetailMhs(judul = "Alamat", isinya = mahasiswa.alamat)
//            Spacer(modifier = Modifier.padding(4.dp))
//            ComponentDetailMhs(judul = "Jenis Kelamin", isinya = mahasiswa.jenisKelamin)
//            Spacer(modifier = Modifier.padding(4.dp))
//            ComponentDetailMhs(judul = "Kelas", isinya = mahasiswa.kelas)
//            Spacer(modifier = Modifier.padding(4.dp))
//            ComponentDetailMhs(judul = "Angkatan", isinya = mahasiswa.angkatan)
//            Spacer(modifier = Modifier.padding(4.dp))
//            ComponentDetailMhs(judul = "Judul Skripsi", isinya = mahasiswa.judulSkripsi)
//            Spacer(modifier = Modifier.padding(4.dp))
//            ComponentDetailMhs(judul = "Dosen Pembimbing 1", isinya = mahasiswa.dosenPembimbingPertama)
//            Spacer(modifier = Modifier.padding(4.dp))
//            ComponentDetailMhs(judul = "Dosen Pembimbing 2", isinya = mahasiswa.dosenPembimbingKedua)
//        }
//    }
//}
//
//@Composable
//fun ComponentDetailMhs(
//    modifier: Modifier = Modifier,
//    judul:String,
//    isinya:String
//){
//    Column(
//        modifier = modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.Start
//    ) {
//        Text(
//            text = "$judul : ",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Gray
//        )
//        Text(
//            text = isinya,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold
//        )
//    }
//}
//
//@Composable
//private fun DeleteConfirmationDialog(
//    onDeleteConfirm: () -> Unit,
//    onDeleteCancel: () -> Unit,
//    modifier: Modifier = Modifier
//){
//    AlertDialog(onDismissRequest = { /*Do nothing*/ },
//        title = { Text("Delete Data") },
//        text = { Text("Apakah anda yakin ingin menghapus data?") },
//        dismissButton = {
//            TextButton(onClick = { onDeleteCancel() }) {
//                Text(text = "Cancel")
//            }
//        },
//        confirmButton = {
//            TextButton(onClick = { onDeleteConfirm() }) {
//                Text(text = "Yes")
//            }
//        }
//    )
//}