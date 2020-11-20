package co.edu.uniquindio.compilares.analixador.sintactico

import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView

open class Sentencia {
    fun getArbolVisual(): TreeItem<String>{
        return TreeItem("Sentencia")
    }
}