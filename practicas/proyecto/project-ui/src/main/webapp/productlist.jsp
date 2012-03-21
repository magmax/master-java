<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html>
    <body>

    <center>
        <table class="ui-widget-content">
            <thead >
                <tr class="ui-widget-header">
                    <td/></td>
                    <td>Referencia</td>
                    <td>Producto</td>
                    <td>Precio</td>
                    <td>Descripción</td>
                </tr>
            </thead>
            <tbody>
                <logic:iterate name="product_list" id="each" type="org.magmax.master.project.ui.shop.ProductForm">
                    <tr>
                        <td><span class="ui-icon ui-icon-cart" title="Comprar" name="buy" 
                                  onclick="buy('<bean:write name="each" property="id"/>', '<bean:write name="each" property="name"/>', '<bean:write name="each" property="price"/>', '<bean:write name="each" property="description"/>')">
                            </span>
                        </td>
                        <td><bean:write name="each" property="id"/></td>
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
