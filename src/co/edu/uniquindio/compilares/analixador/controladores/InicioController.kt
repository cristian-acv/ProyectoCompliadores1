package co.edu.uniquindio.compilares.analixador.controladores

import co.edu.uniquindio.compilares.analixador.lexico.AnalixadorLexico
import co.edu.uniquindio.compilares.analixador.lexico.Categoria
import javafx.concurrent.Task
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextArea
import javafx.scene.control.cell.PropertyValueFactory

class InicioController {

    @FXML lateinit var codigoFuente:TextArea

   // @FXML lateinit var tablaTokens:
    @FXML  var tablaTokens = TableView<Token>()
    @FXML var colLexema = TableColumn<Token, String>()
    @FXML var colCategoria = TableColumn<Token, String>()
    @FXML var colFila = TableColumn<Token, String>()
    @FXML var colColumna = TableColumn<Token, String>()


    /*
     * fun analizarCodigo(e: ActionEvent){
        if(codigoFuente.text.length >0){
            val lexico = AnalixadorLexico(codigoFuente.text)
            lexico.analizar()

            print(lexico.listaToken)

        }

    }
     */


    fun analizarCodigo() {



        val task = object : Task<List<Token>>() {
            override fun call(): List<Token> {
                return datosLista()
            }

            override fun succeeded() {
                tablaTokens.items.clear()
                tablaTokens.items.addAll( value )
            }
        }

        Thread(task).start()
    }

    fun initialize() {

        colLexema.cellValueFactory = PropertyValueFactory<Token, String>("Lexema")
        colCategoria.cellValueFactory = PropertyValueFactory<Token, String>("Categoria")
        colFila.cellValueFactory = PropertyValueFactory<Token, String>("Fila")
        colColumna.cellValueFactory = PropertyValueFactory<Token, String>("Columna")

    }


    fun datosLista() : List<Token> {
        val lexico = AnalixadorLexico(codigoFuente.text)
        lexico.analizar()

        val i=0


            val participants = listOf( Token(lexico.listaToken[i].lexema,lexico.listaToken[i].categoria,
            lexico.listaToken[i].fila,lexico.listaToken[i].columna))




         return participants


    }

    data class Token(val Lexema: String, val Categoria: Categoria, val Fila: Int, val Columna:Int)


}