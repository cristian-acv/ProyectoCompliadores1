package co.edu.uniquindio.compilares.analixador.sintactico

import co.edu.uniquindio.compilares.analixador.lexico.Token

open class Ciclo(var expresionLogica: Token?, var listaSentencia: ArrayList<Sentencia>) :Sentencia(){
    override fun toString(): String {
        return "Ciclo(expresionLogica=$expresionLogica, listaSentencia=$listaSentencia)"
    }
}