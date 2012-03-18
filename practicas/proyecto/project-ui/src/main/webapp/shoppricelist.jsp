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
        <html:base/>
    </head>
    <body>

    <center>
        <table class="ui-widget-content">
            <thead >
                <tr class="ui-widget-header">
                    <td/></td>
                    <td>Producto</td>
                    <td>Precio</td>
                    <td>Descripción</td>
                </tr>
            </thead>
            <tbody>
                <logic:iterate name="pricelist" id="each" type="org.magmax.master.project.ui.shop.ProductForm">
                    <tr>
                        <td><span class="ui-icon ui-icon-cart" title="Comprar"></span></td>
                        <td><bean:write name="each" property="name"/></td>
                        <td><bean:write name="each" property="price"/></td>
                        <td><bean:write name="each" property="description"/></td>
                    </tr>
                </logic:iterate>
            </tbody>
        </table>
    </center>
    </body>
</html:html>
