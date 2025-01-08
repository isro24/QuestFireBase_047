package com.example.praktikumfirebase.di

import com.example.praktikumfirebase.repository.NetworkReposirotyMhs
import com.example.praktikumfirebase.repository.RepositoryMhs
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val repositoryMhs: RepositoryMhs
}

class MahasiswaContainer: AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val repositoryMhs: RepositoryMhs by lazy {
        NetworkReposirotyMhs(firestore)
    }
}