package co.edu.uniquindio.compilares.analixador.lexico

import co.edu.uniquindio.compilares.analixador.lexico.Categoria

class Token (var lexema:String, var categoria: Categoria, var fila:Int, var columna:Int){
    override fun toString(): String {
        return "co.edu.uniquindio.compilares.analixador.lexico.Token(lexema='$lexema', categoria=$categoria, fila=$fila, columna=$columna)"
    }
}