package co.edu.uniquindio.compilares.analixador.controladores

import co.edu.uniquindio.compilares.analixador.lexico.AnalixadorLexico
import co.edu.uniquindio.compilares.analixador.lexico.Token
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextArea

class InicioController {

    @FXML lateinit var codigoFuente:TextArea

   // @FXML lateinit var tablaTokens:
    @FXML  var tablaTokens = TableView<Token>()
    @FXML var colLexema = TableColumn<Token, String>()
    @FXML var colCategoria = TableColumn<Token, String>()
    @FXML var colFila = TableColumn<Token, String>()
    @FXML var colColumna = TableColumn<Token, String>()


    fun analizarCodigo(e: ActionEvent){
        if(codigoFuente.text.length >0){
            val lexico = AnalixadorLexico(codigoFuente.text)
            lexico.analizar()

            print(lexico.listaToken)

        }

    }
}