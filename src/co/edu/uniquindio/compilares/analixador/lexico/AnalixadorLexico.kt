package co.edu.uniquindio.compilares.analixador.lexico

class AnalixadorLexico ( var codigoFuente:String){
    var posicionActual =0
    var caracterActual = codigoFuente[0]
    var listaToken = ArrayList<Token>()
    var finCodigo = 0.toChar()
    var filaActual =0
    var columnaACtual =0



    /*
     * Permite aumentar la posicion
     */
    fun obtenerSiguienteCaracter(){
     if(posicionActual == codigoFuente.length-1){
         caracterActual = finCodigo
     }else{
         if(caracterActual == '\n'){
             filaActual++
             columnaACtual =0
         }else{
             columnaACtual++
         }
         posicionActual++
         caracterActual = codigoFuente[posicionActual]
     }
    }

    /*
     * Permite almacenar tokens
     */
    fun almacenarToken(lexema:String, categoria: Categoria, fila:Int, columna:Int) = listaToken.add(Token(lexema, categoria, fila, columna))

    fun analizar(){
        while (caracterActual != finCodigo){
            if(caracterActual == ' ' || caracterActual =='\t' || caracterActual=='\n'){
              obtenerSiguienteCaracter()
                continue
            }
            if(esEntero()) continue
            if(esdecimal()) continue
            if (esIndenticador()) continue

            almacenarToken(""+caracterActual, Categoria.DESCONOCIDO, filaActual, columnaACtual)
            obtenerSiguienteCaracter()
        }
    }
    /*
     * Automa finito determinista para saber si es un numero
     */
    fun esEntero():Boolean{
        if(caracterActual.isDigit()){
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual
            var posicionInicial = posicionActual

            lexema+=caracterActual
            obtenerSiguienteCaracter()

            while (caracterActual.isDigit()){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }
            if(caracterActual=='.'){
                hacerBT(posicionInicial,filaInicial,columnaIncial)
                return  false
            }

            almacenarToken(lexema, Categoria.ENTERO,filaInicial, columnaIncial)
            return  true
        }

        return  false
    }

 fun hacerBT(posiconInicial:Int, filaInicial:Int,columnaInicial: Int){

     posicionActual=posiconInicial
     filaActual = filaInicial
     columnaACtual=columnaInicial

     caracterActual = codigoFuente[posicionActual]
 }


    /*
    * Automa finito determinista para saber si es un Identificador
    */
    fun esIndenticador():Boolean{
        if(caracterActual.isLetter() || caracterActual =='$' || caracterActual == '_'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            lexema+=caracterActual
            obtenerSiguienteCaracter()

            while (caracterActual.isLetter() || caracterActual =='$' || caracterActual == '_' || caracterActual.isDigit()){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }

            almacenarToken(lexema, Categoria.IDENTIFICADOR,filaInicial, columnaIncial)
            return  true
        }

        return  false
    }

    /*
    * Automa finito determinista para saber si es un decimal
    *
    */

    fun esdecimal():Boolean{
        if(caracterActual== '.' || caracterActual.isDigit()){
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            if(caracterActual=='.'){
                lexema +=caracterActual
                obtenerSiguienteCaracter()

                if(caracterActual.isDigit()){
                    lexema+=caracterActual
                    obtenerSiguienteCaracter()
                    while(caracterActual.isDigit()){
                        lexema+=caracterActual;
                        obtenerSiguienteCaracter()
                    }
                    almacenarToken(lexema, Categoria.DECIMAL,filaInicial,columnaIncial)
                    return true;
                }
            }else{
                lexema+=caracterActual
                obtenerSiguienteCaracter()
                while(caracterActual.isDigit()){
                    lexema+=caracterActual;
                    obtenerSiguienteCaracter()
                }

                if(caracterActual=='.') {
                    lexema += caracterActual
                    obtenerSiguienteCaracter()


                }
            }
            while(caracterActual.isDigit()){
                lexema+=caracterActual;
                obtenerSiguienteCaracter()
            }
            almacenarToken(lexema, Categoria.DECIMAL,filaInicial,columnaIncial)
            return true;
        }
        return false
    }

    /*
     * Permite almacenar tokens
     */
    fun esParentesis(){
        obtenerSiguienteCaracter()
    }
}
