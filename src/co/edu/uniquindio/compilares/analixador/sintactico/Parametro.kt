package co.edu.uniquindio.compilares.analixador.sintactico

import co.edu.uniquindio.compilares.analixador.lexico.Token

class Parametro(var nombre:Token, var tipoDato:Token) {
    override fun toString(): String {
        return "Parametro(nombre=$nombre, tipoDato=$tipoDato)"
    }
}