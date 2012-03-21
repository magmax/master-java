<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:html>

    <body>

    <center>
        <div>
            <table class="ui-widget-content">
                <thead >
                    <tr class="ui-widget-header">
                        <td>#Factura</td>
                        <td>Fecha</td>
                        <td>Usuario</td>
                        <td>Producto</td>
                        <td>Unidades</td>
                        <td>Precio</td>
                        <td>Total</td>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="invoice_list" id="each" type="org.magmax.master.project.ui.invoice.InvoiceForm">
                        <tr>
                            <td><bean:write name="each" property="id"/></td>
                            <td><bean:write name="each" property="date"/></td>
                            <td><bean:write name="each" property="username"/></td>
                            <td><bean:write name="each" property="productName"/></td>
                            <td><bean:write name="each" property="price"/></td>
                            <td><bean:write name="each" property="units"/></td>
                            <td><bean:write name="each" property="total"/></td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
            <br/>
            <a href="javascript:void(formalize())" class="button" style="display:none" id="formalization">Formalizar compra</a>
        </div>
    </center>

</body>
</html:html>
