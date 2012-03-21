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
    </ul>

    <h2>Creación de usuarios</h2>

    <p>Se deberá insertar un usuario inválido (con contraseña) para acceder a la ventana de registro.</p>

    <html:link forward="welcome" styleClass="button" >Volver</html:link>
</body>
</html:html>
