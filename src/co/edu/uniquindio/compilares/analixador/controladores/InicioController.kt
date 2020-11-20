package co.edu.uniquindio.compilares.analixador.controladores

import co.edu.uniquindio.compilares.analixador.lexico.AnalixadorLexico
import co.edu.uniquindio.compilares.analixador.lexico.Categoria
import co.edu.uniquindio.compilares.analixador.sintactico.AnalizadorSintactico
import javafx.concurrent.Task
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextArea
import javafx.scene.control.TreeView
import javafx.scene.control.cell.PropertyValueFactory

class InicioController {

    @FXML lateinit var codigoFuente:TextArea

   // @FXML lateinit var tablaTokens:
    @FXML  var tablaTokens = TableView<Token>()
    @FXML var colLexema = TableColumn<Token, String>()
    @FXML var colCategoria = TableColumn<Token, String>()
    @FXML var colFila = TableColumn<Token, String>()
    @FXML var colColumna = TableColumn<Token, String>()
    @FXML  var arbolVisual = TreeView<String>()


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

        val lexico2 = AnalixadorLexico(codigoFuente.text)
        val sintaxis = AnalizadorSintactico(lexico2.listaToken)
        val uc = sintaxis.esUnidadDeCompilacion()
        if(uc!=null){

            print("Entro3")
            arbolVisual.root= uc.getArbolVisual()
        }

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
        print(lexico.listaToken)

          val participants = mutableListOf<Token>()
        var i=0

      while(lexico.listaToken.size >i){

            participants.add(Token(lexico.listaToken[i].lexema,lexico.listaToken[i].categoria,
                    lexico.listaToken[i].fila,lexico.listaToken[i].columna))
          i+=1
        }





         return participants


    }

    data class Token(val Lexema: String, val Categoria: Categoria, val Fila: Int, val Columna:Int)


}