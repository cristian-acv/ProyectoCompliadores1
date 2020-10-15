package co.edu.uniquindio.compilares.analixador.controladores

import co.edu.uniquindio.compilares.analixador.lexico.AnalixadorLexico
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.scene.control.TextField

class InicioController {

    @FXML lateinit var codigoFuente:TextArea

    @FXML
    fun analizarCodigo(e:ActionEvent){
        if(codigoFuente.text.length >0){
            val lexico = AnalixadorLexico(codigoFuente.text)
            lexico.analizar()

            print(lexico.listaToken)
        }

    }
}