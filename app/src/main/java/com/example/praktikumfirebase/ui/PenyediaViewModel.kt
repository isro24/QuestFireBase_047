package com.example.praktikumfirebase.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.praktikumfirebase.MahasiswaApp
import com.example.praktikumfirebase.ui.home.viewmodel.HomeViewModel
import com.example.praktikumfirebase.ui.insert.viewmodel.InsertViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                MahasiswaApp().container.repositoryMhs
            )
        }
        initializer {
            InsertViewModel(
                MahasiswaApp().container.repositoryMhs
            )
        }
    }
}

fun CreationExtras.MahasiswaApp(): MahasiswaApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApp)