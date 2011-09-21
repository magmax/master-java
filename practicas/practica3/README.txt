Máster en Java-J2EE-XML-AJAX
Grupo Syncrom
http://www.syncrom.com/


PRÁCTICA 3. JUEGO DEL AHORCADO
------------------------------

TEMARIO TRATADO

* Creación de interfaces gráficas con AWT.
* Gestión de eventos.
* Acceso a ficheros de texto.

DESCRIPCIÓN

Se trata de realizar una aplicación que implemente una versión del juego del 
ahorcado. La ventana principal tendrá tres botones: "Nueva partida", 
"Administrar" y "Salir".

El programa consta de dos módulos: El módulo de juego y el de administración.

MODULO: Juego

Esta parte del programa implementa la funcionalidad en modo jugador. Al él se 
accede pulsando el botón “Nueva partida” en la ventana de inicio. El aspecto de 
la ventana que se abrirá al pulsar ese botón deberá tener un aspecto similar al
de la siguiente figura:
       Introducir letra: __     [COMPROBAR]

       __ __________ ______

       Intentos restantes: 4
 
Al abrirse esta ventana, se mostrará en un Label el título de la película a 
adivinar, sustituyéndose cada uno de los caracteres que la forman por el 
carácter de subrayado “_”. El titulo se extraerá aleatoriamente de un fichero 
de texto donde se encuentran almacenados todos los títulos disponibles, cada 
uno en una fila.
La mecánica del juego es bien sencilla, el usuario consta inicialmente con una 
serie de intentos (por ejemplo 4) que se irán reduciendo en una unidad cada vez
que el usuario falle al intentar descubrir una de las letras que contiene el
título. Para intentar descubrir una letra, el usuario introducirá la letra en
la caja de texto que aparece en la parte superior de la ventana, pulsando a 
continuación el botón “comprobar”. Si la letra se encuentra en el título, se
sustituirá el carácter “_” por dicha letra en los lugares donde esta aparezca,
sino, se descontará un intento al usuario.
Si el usuario acierta todas las letras del título se le mostrará un cuadro de
diálogo con un mensaje de enhorabuena y se cerrará la ventana para volver a la
de inicio. Si el usuario se queda sin intentos, se le mostrará un cuadro de 
diálogo con un mensaje indicando que no dispone de más intentos y se cerrará
también la ventana.


MÓDULO: Administrador

Se utilizará para añadir nuevos títulos de películas al fichero de texto. Al él
se accede desde el botón “administrar” de la ventana de inicio, mostrándose la
ventana que se indica en la siguiente figura:
       Introduzca nuevo título: _______________________

       [Añadir]

En la caja de texto se escribirá el nuevo título que se quiere añadir, proceso
que tendrá lugar al pulsar el botón “Añadir”.

