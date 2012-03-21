<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>"Tienda Virtual"</title>
        <link rel="stylesheet" type="text/css" href="style/main.css" />

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $( ".button" ).button();
            });
        </script>
        <html:base/>
    </head>
    <body>


    <center>
        <h1>Acerca de...</h1>
        <br/>
    </center>
    <p>
        Esta aplicación ha sido desarrollada para cumplir los objetivos del 
        "<a href="http://www.syncrom.com/temarios/java/master_java_online.asp">Máster OnLine de Java-J2EE-XML-AJAX</a>".
        El autor de la misma es <a href="mailto:miguelangel.garcia@gmail.com">Miguel Ángel García Martínez</a>, y puede 
        distribuirse bajo <a href="http://www.gnu.org/copyleft/gpl.html">licencia GPL</a>.
    </p>

    <p>
        Para su desarrollo se han utilizado distintas tecnologías:
    </p>
    <ul>
        <li>Struts: Para las vistas y el controlador.</li>
        <li>JPA: Para la persistencia</li>
        <li>JQuery: para los estilos de la web, iconos y Ajax.</li>
        <li>Maven: para la gestión de proyectos.</li>
        <li>Git: para el control de versiones.</li>
    </ul>

    <h2>Autenticación</h2>

    <p>Si la contraseña introducida no es válida, se accederá a la ventana de registro. 
        Igualmente puede accederse a esta ventana pulsando el botón que lo indica.</p>

    <p>Es necesario introducir el usuario y la contraseña. Si no fuera así, se indicará
        con un icono cuya leyenda muestra el error.</p>
    
    
    <h2>Registro</h2>
    
    <p>Se deben rellenar, al menos, los tres primeros campos: usuario, contraseña y email.</p>
    
    <p>El email debe tener el formato apropiado. Ante cualquier problema, se mostrará un icono cuya leyenda indica el error exacto.</p>
    
    <p>La aplicación soporta varios teléfonos y emails, pero la parte web sólo dejará insertar uno de ellos.</p>
    
    
    <h2>Tienda</h2>
    
    <p>Si hemos conseguido entrar correctamente, se mostrará la tienda.</p>
    
    <p>Será necesario seleccionar una sección. Al hacerlo se realiza la consulta automáticamente, mostrando (vía AJAX) los productos disponibles.</p>
    
    <p>Si pulsamos sobre el icono del carrito de la compra, aparecerá el producto en la lista de la compra actual. El botón se mostrará así: <span class="ui-icon ui-icon-cart"></span></p>
    
    <p>Igualmente, si pulsamos sobre el botón de la papelera, se eliminará de la lista. El botón será similar a éste icono: <span class="ui-icon ui-icon-trash"></span></p>
    
    <p>Para formalizar la compra basta pulsar el botón, que nos llevará a la ventana que indica el éxito de la misma.</p>
    
    <p>Si el usuario fuera administrador, se mostrará un botón "Facturación" que permite acceder a la ventana de administración de facturas.</p>
    
    
    <h2>Facturación</h2>
    
    <p>Desde esta ventana se pueden consultar las compras anteriores.</p>
    
    <p>Habrá que indicar la fecha de inicio y la fecha de fin para poder filtrar las facturas. Una vez insertadas, pulsaremos sobre el botón "Buscar" y aparecerán justo debajo.</p>
    
    <p>Los botones de las fechas son "inteligentes" y no nos dejarán introducir un rango de fechas inválido.</p>
    
    <html:link forward="welcome" styleClass="button" >Volver</html:link>
</body>
</html:html>
