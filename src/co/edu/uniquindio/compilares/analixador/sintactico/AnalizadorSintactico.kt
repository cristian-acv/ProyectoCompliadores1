package co.edu.uniquindio.compilares.analixador.sintactico

import co.edu.uniquindio.compilares.analixador.controladores.InicioController
import co.edu.uniquindio.compilares.analixador.lexico.Categoria
import co.edu.uniquindio.compilares.analixador.lexico.Token

class AnalizadorSintactico (var listaTokens:ArrayList<Token>){

    var posicionActual=0;
    var tokenActual = listaTokens[posicionActual]
    var listaErrores= ArrayList<Error>()

    fun obetenerSiguienToken(){

        posicionActual++
         if(posicionActual < listaTokens.size){
             tokenActual=listaTokens[posicionActual]
         }
    }

    fun reportarError(mensaje:String){
         listaErrores.add(Error(mensaje,tokenActual.fila,tokenActual.columna))
    }

    /**
     * <UnidadDeCompilacion> :: <ListaFunciones>
     */
    fun esUnidadDeCompilacion(): UnidadDeCompilacion? {
        val listaFunciones: ArrayList<Funcion> = esListaFunciones()
        return if (listaFunciones.size > 0) {
            UnidadDeCompilacion(listaFunciones)
        } else null
    }

    /**
     * <ListaFunciones>::= <Funcion>[<ListaFunciones>]
     */
    fun esListaFunciones(): ArrayList<Funcion>{
       var listaFunciones= ArrayList<Funcion>()
        var funcion = esFuncion()
        while (funcion!=null){
            listaFunciones.add(funcion)
            funcion = esFuncion()
        }
        return listaFunciones
    }

    /**
     * <Funcion>::= ? <TipoRetorno>  identificador "("[<ListaParametros>]")"<BloqueSentencias>
     */
    fun esFuncion():Funcion?{

     if(tokenActual.categoria ==Categoria.PALABRA_RESERVADA && tokenActual.lexema == "?") {
         obetenerSiguienToken()
         var tipoRetorno = esTipoRetorno()
         if (tipoRetorno != null) {
             obetenerSiguienToken()
             if (tokenActual.categoria == Categoria.IDENTIFICADOR) {
                 var nombreFuncion = tokenActual
                 obetenerSiguienToken()
                 if (tokenActual.categoria == Categoria.PARENTESIS_IZQ) {
                     obetenerSiguienToken()
                     var listaParametros = esListaParametros()
                     if (tokenActual.categoria == Categoria.PARENTESIS_DER) {
                         obetenerSiguienToken()
                         val bloqueSentencias = esBloqueSentencias()
                         if (bloqueSentencias != null) {
                             //Funcion esta bien escrita
                             return Funcion(nombreFuncion, tipoRetorno, listaParametros, bloqueSentencias)
                         } else {
                             reportarError("El bloque de sentencia esta vacio")
                         }
                     } else {
                         reportarError("Falta el parentesis derecho")
                     }
                 } else {
                     reportarError("Falta el parentesis izquierdo")
                 }
             } else {
                 reportarError("Falta el nombre de la funcion")
             }
         } else {
             reportarError("Falta el tipo de retorno en la funcion")
         }
     }
        return  null
    }

    /**
     * <TipoRetorno> :: int | decimal | string | char | vf | void
     */
    fun esTipoRetorno():Token?{
       if(tokenActual.categoria == Categoria.PALABRA_RESERVADA){
           if(tokenActual.lexema == "INT" || tokenActual.lexema == "decimal" ||
                   tokenActual.lexema == "string" || tokenActual.lexema =="vf"||
                   tokenActual.lexema == "char" || tokenActual.lexema=="void"){
           return tokenActual
           }
       }
        return null
    }

    /**
     * <TipoDato> :: int | dl | string | char | vf
     */
    fun esTipoDato():Token?{
        if(tokenActual.categoria == Categoria.PALABRA_RESERVADA){
            if(tokenActual.lexema == "INT" || tokenActual.lexema == "decimal" ||
                    tokenActual.lexema == "string" || tokenActual.lexema =="vf"||
                    tokenActual.lexema == "char" ){
                return tokenActual
            }
        }
        return null
    }

    /**
     * <ListaParametros> ::= <Parametro>[","<ListaParametros>]
     */

    fun esListaParametros():ArrayList<Parametro> {
        var parametros = ArrayList<Parametro>()
        var p = esParametro()
        var errorBarraBaja= false

        while (p != null) {
            parametros.add(p)
            when (tokenActual.categoria) {
                Categoria.BARRA_BAJA ->{
                    obetenerSiguienToken()
                    p=esParametro()
                }
                Categoria.IDENTIFICADOR ->{
                    errorBarraBaja = true
                    p=esParametro()
                }
                else ->{
                    p =null
            }

            }}
          if(errorBarraBaja){
    reportarError("Falta la _ para separa los parametros")
          }

        return parametros
    }

    /**
     * <Parametro>  :: = identificador":"<TipoDato>
     */
    fun esParametro():Parametro?{
        if (tokenActual.categoria == Categoria.IDENTIFICADOR){
            val nombre = tokenActual
            obetenerSiguienToken()

            if(tokenActual.categoria == Categoria.DOS_PUNTOS){
                obetenerSiguienToken()

                val tipoDato = esTipoDato()

                if(tipoDato!=null){
                    obetenerSiguienToken()

                    return Parametro(nombre, tipoDato)
                }else{
                    reportarError("Falta el tipo de retorno")
                }
            }else{
                reportarError("Falta el operador :")
            }

        }
        return  null
    }

    /**
     * <BloqueSentencias> ::= "{" [<ListaSentencias>] "}"
     */
    fun esBloqueSentencias():ArrayList<Sentencia>?{

        if(tokenActual.categoria == Categoria.LLAVE_IZQ){
            obetenerSiguienToken()

            var listaSentencias= esListaSentencias()

            if(tokenActual.categoria == Categoria.LLAVE_DER){
                obetenerSiguienToken()

                return listaSentencias
            } else{
                reportarError("Falta llave derecha en la funcion")
            }
        } else{
            reportarError("Falta llave Izquierda en la funcion")
        }
        return null
    }
    /**
     * <ListaSentencias> :: <Sentencia>[<ListaSentencias>]
     */
     fun esListaSentencias():ArrayList<Sentencia>{
    val lista = ArrayList<Sentencia>()
    var s = esSentencia()

    while (s != null){
        lista.add(s)
        s=esSentencia()
    }
    return lista
}

    /**
     *<Sentencia> :: <Decision> | <Ciclo> | <Impresion> | <Asignacion> | <DeclracionVariable> | <InvocacionFuncion> |
     * <Arreglo> | <Retorno> |
     */

   fun esSentencia(): Sentencia? {
      var s:Sentencia? = esArreglo()
        if(s != null){
            return s
        }
        s= esAsignacion()
        if(s != null){
            return  s
        }
        s= esCiclo()
        if(s != null){
            return  s
        }
        s= esDecision()
        if(s != null){
            return  s
        }
        s= esDeclaracionVariable()
        if(s != null){
            return  s
        }
        s= esImpresion()
        if(s != null){
            return  s
        }
        s= esInvocacionFuncion()
        if(s != null){
            return  s
        }

        s=esRetorno()
        return  s

    }

    private fun esRetorno(): Sentencia? {
  return null
    }

    private fun esInvocacionFuncion(): Sentencia? {
        return null
    }

    private fun esImpresion(): Sentencia? {
        return null

    }

    private fun esDeclaracionVariable(): Sentencia? {
        return null

    }

    /**
     * <Decision> :: if <ExpresionLogica> do <Bloque> [else <Bloque>]
     */
    private fun esDecision(): Decision? {

        return null

    }

    /**
     * <Ciclo> :: while <ExpresionLogica> <Bloque> end
     */

    private fun esCiclo(): Ciclo? {
           if(tokenActual.categoria== Categoria.PALABRA_RESERVADA && tokenActual.lexema=="whl"){

               obetenerSiguienToken()
               val expresion = esExpresionLogica()
               if(expresion != null ){
                   obetenerSiguienToken()
                   if(tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema =="end"){
                       obetenerSiguienToken()
                       val  bloque = esBloqueSentencias()
                       if(bloque != null){
                           return  Ciclo(expresion, bloque)
                       }
                   }else{
                       reportarError("Falta la palabra do")
                   }
               }else{
                   reportarError("Hay un error en la condicion")
               }

           }
        return null
    }

    /**
     * <ExpresionLogica> :: DI <OperadorLogico> Di <=
     */
    fun esExpresionLogica():Token?{
        if(tokenActual.categoria == Categoria.DECIMAL || tokenActual.categoria == Categoria.ENTERO){

        }
        return null
    }
    /**
     * <ExpresionLogica> ::  | != | < | > | >= | <=
     */
    fun OperadorRelacional(): Sentencia?{
        return null
    }

    private fun esAsignacion(): Sentencia? {
        return null

    }

    private fun esArreglo(): Sentencia? {
        return null

    }

    /**
     * <Expresion> ::= <ExpresionArimetica> | < ExpresionRelacional> | <ExpresionLogica> | <ExpresionCadena>
     */
    
    fun esExpresion():Expresion?{
       return  null
    }

    private fun esPresionCadena(): Expresion? {
        return null

    }

    /**
     * <ExpresionArimetica> ::= "("<ExpArimetica>")" [operadorAritmetico <ExpArimetica>] |
     * <ValorNumerico>
     */
    private fun esExpresionAritmetica(): Expresion? {
        return null

    }


    fun esValornumerico(){

    }
}