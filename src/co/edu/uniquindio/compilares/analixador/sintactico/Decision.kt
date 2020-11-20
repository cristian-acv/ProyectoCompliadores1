package co.edu.uniquindio.compilares.analixador.sintactico

import javafx.scene.control.TreeItem

class Decision(var expresionLogica: ExpresionLogica, var listaSentencia: ArrayList<Sentencia>, var listaSentenciaElse:ArrayList<Sentencia>? ): Sentencia() {
    override fun toString(): String {
        return "Decision(expresionLogica=$expresionLogica, listaSentencia=$listaSentencia)"
    }


}