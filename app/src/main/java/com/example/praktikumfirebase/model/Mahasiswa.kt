package com.example.praktikumfirebase.model


data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String,
    val judulSkripsi: String,
    val dosenPembimbingPertama: String,
    val dosenPembimbingKedua: String
){
    constructor() : this("", "", "", "", "",  "", "", "", "")
}