<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
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
        </script>
        <html:base/>
    </head>
    <body>

    <center>
        <h1>Facturación</h1>
        <br/>
        
        <div>
            <p>
                Del ... al ... Filtrar
            </p>  
        </div>
            
        <div id="invoices">
           
        </div>
    </center>
    <html:link forward="welcome" styleClass="button" >Volver</html:link>
    <center>
        <hr width="80%"/>
        <html:link forward="about">About...</html:link>
    </center>
</body>
</html:html>
