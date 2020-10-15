package co.edu.uniquindio.compilares.analixador.app

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.application.Application as Application

class Aplicacion : Application() {
    override fun start(primaryStage: Stage?) {

        val loader = FXMLLoader(Aplicacion::class.java.getResource("/inicio.fxml"))
        val parent: Parent= loader.load()

        val scene = Scene(parent)

        primaryStage?.scene = scene
        primaryStage?.title = "Mi compilador"
        primaryStage?.show()
    }
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
     launch(Aplicacion::class.java)
        }
    }
}