package co.edu.uniquindio.compilares.analixador

import co.edu.uniquindio.compilares.analixador.lexico.AnalixadorLexico
import co.edu.uniquindio.compilares.analixador.sintactico.AnalizadorSintactico

fun main(){

    val lexico = AnalixadorLexico("? INT $"+"sumar$(){whl == end {}}")
    lexico.analizar()
    //print(lexico.listaToken)
    val sintaxis = AnalizadorSintactico(lexico.listaToken)
    print(sintaxis.esUnidadDeCompilacion())
    print(sintaxis.listaErrores)
}