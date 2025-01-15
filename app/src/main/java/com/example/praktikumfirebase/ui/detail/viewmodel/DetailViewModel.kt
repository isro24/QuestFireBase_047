//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.praktikumfirebase.model.Mahasiswa
//import com.example.praktikumfirebase.repository.RepositoryMhs
//import com.example.praktikumfirebase.ui.navigation.DestinasiDetail
//import kotlinx.coroutines.launch
//
//class DetailViewModel(
//    savedStateHandle: SavedStateHandle,
//    private val repositoryMhs: RepositoryMhs
//) : ViewModel() {
//    private val nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])
//
//    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
//        private set
//
//    init {
//        getMhs()
//    }
//
//    private fun getMhs() {
//        viewModelScope.launch {
//            detailUiState = DetailUiState(isLoading = true)
//            try {
//                val result = repositoryMhs.getMhs(nim)
//                detailUiState = DetailUiState(
//                    detailUiEvent = result.toDetailUiEvent(),
//                    isLoading = false
//                )
//            } catch (e: Exception) {
//                detailUiState = DetailUiState(
//                    isLoading = false,
//                    isError = true,
//                    errorMessage = e.message ?: "Unknown"
//                )
//            }
//        }
//    }
//}
//
//
//data class DetailUiState(
////    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
//    val isLoading: Boolean = false,
//    val isError: Boolean = false,
//    val errorMessage: String = ""
//){
////    val isUiEventEmpty: Boolean
//////        get() = detailUiEvent == InsertUiEvent()
////
////    val isUiEventNotEmpty: Boolean
////        get() = detailUiEvent != InsertUiEvent()
////}
//
////fun Mahasiswa.toDetailUiEvent(): InsertUiEvent{
//////    return InsertUiEvent(
////        nim = nim,
////        nama = nama,
////        jenisKelamin = jenisKelamin,
////        alamat = alamat,
////        kelas = kelas,
////        angkatan = angkatan
////    )
////}