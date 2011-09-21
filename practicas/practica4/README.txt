Máster en Java-J2EE-XML-AJAX
Grupo Syncrom
http://www.syncrom.com/


PRÁCTICA 2. GESTIÓN DE LIBRERÍA
-------------------------------

TEMARIO TRATADO

* Creación de interfaces gráficas con AWT y gestión de eventos..
* Acceso a ficheros

DESCRIPCIÓN

Realizar una aplicación basada en interfaz gráfica AWT que permita el 
almacenamiento, recuperación de los datos de una persona en un fichero, así 
como la modificación de los mismos.

Los datos serán manejados en un objeto Persona de tipo JavaBean con los 
siguientes campos: nombre, apellidos, edad y telefono.

Al iniciarse la aplicación se intentarán localizar en un fichero determinado el 
objeto Persona almacenado con anterioridad. Si existe, se cargarán los datos en
los campos de texto de la ventana, sino, se mostrará la ventana con los campos 
vacíos. El aspecto de dicha ventana se muestra en la siguiente figura:

    Nombre:   [______________]
    Apellidos:[______________]
    Teléfono: [______________]
    Edad:     [______________]

      ACTUALIZAR         SALIR


En caso de que las cajas de texto muestren datos, estos se podrán modificar
directamente por el usuario, actualizándose en el objeto Persona al pulsar 
“Actualizar”. Si no existen datos previos, se introducirán directamente los 
nuevos valores sobre los campos de texto, generándose un nuevo objeto Persona 
con dichos datos al pulsa el botón “Actualizar”.

Cuando se pulse el botón “salir” se le preguntará al usuario si desea actualizar
la información en disco; si la respuesta es afirmativa, se guardará el objeto 
Persona en el disco, sustituyendo al anterior en caso de ser una modificación 
de datos. A continuación, la ventana se cerrará y el programa se dará por 
finalizado. Si la respuesta es negativa, se cerrará la ventana sin 
guardar/actualizar el objeto Persona.


