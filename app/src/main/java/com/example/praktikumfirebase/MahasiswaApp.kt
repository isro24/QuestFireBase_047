package com.example.praktikumfirebase

import android.app.Application
import com.example.praktikumfirebase.di.AppContainer
import com.example.praktikumfirebase.di.MahasiswaContainer

class MahasiswaApp: Application(){
    lateinit var container: MahasiswaContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}