package co.edu.uniquindio.compilares.analixador.sintactico

import co.edu.uniquindio.compilares.analixador.lexico.Token

class InvocacionFuncion(var nombreFuncion:Token, var ListaArgumentos:ArrayList<Expresion>) : Sentencia() {
    override fun toString(): String {
        return "InvocacionFuncion(nombreFuncion=$nombreFuncion, ListaArgumentos=$ListaArgumentos)"
    }
}