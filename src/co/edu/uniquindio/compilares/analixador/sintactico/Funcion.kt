package co.edu.uniquindio.compilares.analixador.sintactico

import co.edu.uniquindio.compilares.analixador.lexico.Token
import javafx.scene.control.TreeItem

class Funcion(var nombreFuncion:Token, var tipoRetorno: Token,var listaParametros:ArrayList<Parametro>
              ,var listaSentencias:ArrayList<Sentencia>) {
    override fun toString(): String {
        return "Funcion(nombreFuncion=$nombreFuncion, tipoRetorno=$tipoRetorno, listaParametros=$listaParametros, listaSentencias=$listaSentencias)"
    }

    fun getArbolVisual(): TreeItem<String>{
        var raiz = TreeItem("Funcion")

        return  raiz
    }
}