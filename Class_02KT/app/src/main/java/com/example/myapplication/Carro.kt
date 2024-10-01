package com.example.myapplication

class Carro(val llantas: Int, val modelo: String, var antiguo: Int) {
    constructor(llantas: Int, antiguo: Int, modelo: String): this(llantas, modelo, antiguo)
    fun agregarAnios(){
        antiguo++
    }
}