package co.edu.uniquindio.compilares.analixador

import co.edu.uniquindio.compilares.analixador.lexico.AnalixadorLexico

fun main(){

    val lexico = AnalixadorLexico("a   15.5 8529845641268465+ vbhj 545 yghb")
    lexico.analizar()
    print(lexico.listaToken)
}