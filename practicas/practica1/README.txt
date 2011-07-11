Máster en Java-J2EE-XML-AJAX
Grupo Syncrom
http://www.syncrom.com/


PRÁCTICA 1. CLASE PARA LA LECTURA DE NÚMEROS
--------------------------------------------

TEMARIO TRATADO

* Sintaxis del lenguaje Java, incluida orientación a objetos.
* Clases básicas de entrada / salida
* Captura de excepciones y creación de excepciones personalizadas.

DESCRIPCIÓN

Utilizando el mecanismo de la herencia, crear una nueva clase llamada Lector 
para la lectura de datos numéricos de un stream de entrada. Esta nueva clase 
será una subclase de java.io.BufferedReader y tendrá las siguientes 
características:
* Constructores.
  - Lector(). Creará un objeto Lector asociado a la entrada estándar.
  - Lector(Reader in). Creará un objeto Lector asociado al buffer de entrada
      que se le suministre como parámetro.
* Métodos.
Además de los heredados de BufferedReader, la clase Lector añadirá los
siguientes métodos nuevos:
  - int readInt(). Recuperará la última línea del buffer de entrada como un
      entero.
  - double readDouble(). Recuperará la última línea del buffer de entrada
      como un doble.
  - int [] readManyInt(). Devolverá un array con todos los números
      introducidos en la última línea, teniendo en cuenta que cada número está
      separado del siguiente por una coma “,”.
Los tres métodos declararán una excepción llamada NumeroNoValidoException, que 
será lanzada en caso de que el dato recuperado no tenga el formato numérico 
esperado.
Ésta es una excepción personalizada que habrá que definir.
Una vez finalizada la implementación de ambas clases (la clase Lector y la clase
de excepción) se creará un pequeño programa para probar su funcionamiento. Este 
programa consistirá en la solicitud de una serie de números al usuario para 
después mostrar la media de los números introducidos.
