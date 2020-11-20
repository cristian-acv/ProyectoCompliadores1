package co.edu.uniquindio.compilares.analixador.sintactico

import co.edu.uniquindio.compilares.analixador.lexico.Token

class ExpresionCadena() : Expresion() {
    var cadena:Token? = null
    var expresion:Expresion?=null
    var identificador:Token?=null

    constructor(cadena:Token, expresion: Expresion?):this(){
        this.cadena=cadena
        this.expresion=expresion
    }

    constructor(identificador:Token):this(){
        this.identificador=identificador
    }
}