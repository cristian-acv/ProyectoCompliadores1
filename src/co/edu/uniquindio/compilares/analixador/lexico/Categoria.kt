package co.edu.uniquindio.compilares.analixador.lexico

enum class Categoria {
    ENTERO, DECIMAL, IDENTIFICADOR, OPERADOR_ARIMETICO, OPERADOR_LOGICO,PARENTESIS_IZQ,PARENTESIS_DER,
    PALABRA_RESERVADA, DESCONOCIDO, STRING,OPERADOR_INCREMENTO,OPERADOR_DECREMENTO,COMENTARIO_BLOQUE,
    COMENTARIO_LINEA,LLAVE_DER,LLAVE_IZQ,DOS_PUNTOS,BARRA_BAJA
}