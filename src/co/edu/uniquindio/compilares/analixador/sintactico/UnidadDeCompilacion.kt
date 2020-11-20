package co.edu.uniquindio.compilares.analixador.sintactico

import javafx.scene.control.TreeItem

class UnidadDeCompilacion (var listaFunciones:ArrayList<Funcion>){
    override fun toString(): String {
        return "UnidadDeCompilacion(listaFunciones=$listaFunciones)"
    }

    fun getArbolVisual():TreeItem<String>{

         var raiz = TreeItem("Unidad de compilacion")
        for(f in listaFunciones){
           raiz.children.add(f.getArbolVisual())
        }
        return raiz
    }
}