<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<table class="ui-widget-content">
    <thead class="ui-widget-header">
        <tr>
            <td/></td>
            <td/>Producto</td>
            <td/>Precio</td>
            <td/>Descripción</td>
        </tr>
    </thead>
    <tbody>
        <logic:iterate name="sectionlist" id="each" type="org.magmax.master.project.ui.shop.ProductForm">
            <tr>
                <td></td>
                <td><bean:write name="each" property="name"/></td>
                <td><bean:write name="each" property="price"/></td>
                <td><bean:write name="each" property="description"/></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>