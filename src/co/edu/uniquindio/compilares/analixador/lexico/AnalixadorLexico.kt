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
            if(esParenDer())continue
            if(esParenIzq())continue
            if(esPalabraReservada())continue
            if(opeadorDeIncremento())continue
            if(opeadorDecremento())continue
            if(esOperadorArirtmetico())continue
            if(esOperadorLogico()) continue
            if(esPalabraReservadafor()) continue
            if(esCadenaCarateres())continue
            if(esPalabraReservadawhl())continue
            if(esPalabraReservadaif())continue
            if(esComentarioBloque())continue
            if (esComentarioLinea())continue
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
        var cont=0
        if(caracterActual =='$'){

            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            lexema+=caracterActual
            obtenerSiguienteCaracter()

            while ((caracterActual.isLetter()  || caracterActual == '_' || caracterActual.isDigit() )&& cont<10){

                lexema+=caracterActual
                obtenerSiguienteCaracter()

                cont++
            }

            if(caracterActual =='$' && cont<10){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
                almacenarToken(lexema, Categoria.IDENTIFICADOR,filaInicial, columnaIncial)
                return  true
            }else{
                hacerBT( posicionInicial,filaInicial,columnaIncial)
                return false
            }

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
     * Automa finito determinista para saber si es un Parentesis Izquierdo.
     */
    fun esParenIzq():Boolean{


        if(caracterActual== '(') {
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            while (caracterActual== '('){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }

            almacenarToken(lexema, Categoria.PARENTESIS_IZQ,filaInicial,columnaIncial)
            return true;
        }
        return  false;
        }

    /*
     * Automa finito determinista para saber si es un Parentesis Derecho.
     */
    fun esParenDer():Boolean{


        if(caracterActual== ')') {
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            while (caracterActual== ')'){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }

            almacenarToken(lexema, Categoria.PARENTESIS_DER,filaInicial,columnaIncial)
            return true;
        }
        return  false;
    }
    /*
    * Automa finito determinista para saber si es un corchete Derecho.
    */
    fun esCorcheDer():Boolean{


        if(caracterActual== ']') {
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            while (caracterActual== ']'){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }

            almacenarToken(lexema, Categoria.CORCHETE_DER,filaInicial,columnaIncial)
            return true;
        }
        return  false;
    }
    /*
     * Automa finito determinista para saber si es un corchete Izquierdo.
     */
    fun esCorcheIzq():Boolean{


        if(caracterActual== '[') {
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            while (caracterActual== '['){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }

            almacenarToken(lexema, Categoria.CORCHETE_IZQ,filaInicial,columnaIncial)
            return true;
        }
        return  false;
    }


    /*
     * Automa finito determinista para saber si es una palabra reservada ? para definir de variables
     */
    fun esPalabraReservada():Boolean{
        if(caracterActual== '?') {
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            almacenarToken(lexema, Categoria.PALABRA_RESERVADA,filaInicial,columnaIncial)
            return true;
        }
        return  false;
    }

    fun esOperadorLogico():Boolean{
        if(caracterActual=='=' || caracterActual=='<' || caracterActual=='>' || caracterActual=='!'){
            var aux=caracterActual
            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual
            lexema+=caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual=='=' ){
                lexema+=caracterActual
                almacenarToken(lexema, Categoria.OPERADOR_LOGICO,filaInicial,columnaIncial)
                return true
            }
            else{
                if (aux=='='){
                    hacerBT( posicionInicial,filaInicial,columnaIncial)
                    return false
                }
                else {
                    almacenarToken(lexema, Categoria.OPERADOR_LOGICO, filaInicial, columnaIncial)
                    return true
                }

            }
        }
        return false
    }
    fun esOperadorArirtmetico():Boolean{
        if(caracterActual=='+' || caracterActual=='-' || caracterActual=='/'|| caracterActual=='*' ){
            var lexema=""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual
            lexema+=caracterActual
            obtenerSiguienteCaracter()
            almacenarToken(lexema,Categoria.OPERADOR_ARIMETICO,filaInicial,columnaIncial)
            return true
        }
        return false
    }
    fun esPalabraReservadaif():Boolean{
        if(caracterActual== 'i') {
            var lexema = ""
            var filaInicial = filaActual
            var columnaIncial = columnaACtual
            var posicionInicial = posicionActual
             lexema+=caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual== 'f'){

                    lexema+=caracterActual
                    obtenerSiguienteCaracter()
            almacenarToken(lexema, Categoria.PALABRA_RESERVADA,filaInicial,columnaIncial)
            return true;

            }
            hacerBT(posicionInicial,filaInicial,columnaIncial)
        }

        return  false;
    }fun esPalabraReservadafor():Boolean{
        if(caracterActual== 'f') {

            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual
            lexema+=caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual== 'o'){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual== 'r'){
                    lexema+=caracterActual
                    obtenerSiguienteCaracter()
                    almacenarToken(lexema, Categoria.PALABRA_RESERVADA,filaInicial,columnaIncial)
                    return true;
                }
            }
            hacerBT( posicionInicial,filaInicial,columnaIncial)
        }

        return  false;
    }
    fun esPalabraReservadawhl():Boolean{
        if(caracterActual== 'w') {

            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual
            lexema+=caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual== 'h'){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual== 'l'){
                    lexema+=caracterActual
                    obtenerSiguienteCaracter()
                    almacenarToken(lexema, Categoria.PALABRA_RESERVADA,filaInicial,columnaIncial)
                    return true;
                }
            }
            hacerBT( posicionInicial,filaInicial,columnaIncial)
        }

        return  false;
    }
    fun opeadorDeIncremento():Boolean{
        if(caracterActual== '+') {

            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual
            lexema+=caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual== '+'){
                lexema+=caracterActual
                obtenerSiguienteCaracter()

                    almacenarToken(lexema, Categoria.OPERADOR_INCREMENTO,filaInicial,columnaIncial)
                    return true;

            }
            hacerBT( posicionInicial,filaInicial,columnaIncial)
        }

        return  false;
    }
    fun opeadorDecremento():Boolean{
        if(caracterActual== '-') {

            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual
            lexema+=caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual== '-'){
                lexema+=caracterActual
                obtenerSiguienteCaracter()

                almacenarToken(lexema, Categoria.OPERADOR_DECREMENTO,filaInicial,columnaIncial)
                return true;

            }
            hacerBT( posicionInicial,filaInicial,columnaIncial)
        }

        return  false;
    }

    /*
    * Automa finito determinista para saber si es una cadena de Carateres
    */
    fun esCadenaCarateres():Boolean{
        if(caracterActual =='"'){

            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            lexema+=caracterActual
            obtenerSiguienteCaracter()

            while (caracterActual !='"' ){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }

            if(caracterActual =='"' ){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
                almacenarToken(lexema, Categoria.STRING,filaInicial, columnaIncial)
                return  true
            }else{
                hacerBT( posicionInicial,filaInicial,columnaIncial)
                return false
            }

        }

        return  false
    }
    /*
    * Automa finito determinista para saber si es una comentario
    */
    fun esComentarioBloque():Boolean{
        if(caracterActual =='┐'){

            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            lexema+=caracterActual
            obtenerSiguienteCaracter()

            while (caracterActual !='┐' ){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }

            if(caracterActual =='┐' ){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
                almacenarToken(lexema, Categoria.COMENTARIO_BLOQUE,filaInicial, columnaIncial)
                return  true
            }else{
                hacerBT( posicionInicial,filaInicial,columnaIncial)
                return false
            }

        }

        return  false
    }
    /*
    * Automa finito determinista para saber si es una comentario de linea
    */
    fun esComentarioLinea():Boolean{
        if(caracterActual =='┘'){

            var lexema = ""
            var posicionInicial = posicionActual
            var filaInicial = filaActual
            var columnaIncial = columnaACtual

            lexema+=caracterActual
            obtenerSiguienteCaracter()

            while (caracterActual !='\n' ){
                lexema+=caracterActual
                obtenerSiguienteCaracter()
            }
                almacenarToken(lexema, Categoria.COMENTARIO_LINEA,filaInicial, columnaIncial)
                return  true
            }


        return  false
    }
}
