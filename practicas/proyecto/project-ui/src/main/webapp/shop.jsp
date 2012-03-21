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
                $("#pricelist").hide();
                
                $("#section").change(function() {
                    $("#pricelist").slideUp(function() {
                        var sectionname = $("#section").val();
                        if (sectionname != "") {
                            $.post("productlist.do", {"section":sectionname}, function (data) {
                                $("#pricelist").html(data).slideDown();
                            });
                        }
                    });
                });
            });
            
            function buy(id, name, price, description) {
                var row = '<td><span class="ui-icon ui-icon-trash" onclick="deleteRow(this)"/></td>';
                row += '<td name="ref">' + id + "</td>";
                row += '<td name="product">' + name + "</td>";
                row += "<td>" + price + "</td>";
                row += "<td>" + description + "</td>";
                $("#current_cart tbody").append('<tr style="display:none">' + row + "</tr>");
                $("#current_cart tbody tr:last").fadeIn(1000); 
                $("#formalization").show("fade");
            }
            
            function deleteRow(span) {
                var row = $(span).parent().parent();
                row.fadeOut(function() {
                    row.remove();
                    row = null;
                    if ($("#current_cart tbody tr").size() == 0) {
                        $("#formalization").fadeOut();
                    }
                });
            }
            
            function formalize() {
                var products = new Array();
                $.each($("#current_cart td[name='ref']"), function(idx, item) {
                    products.push (item.innerText);
                });
                $.post("buy.do", {"products": products.join(',')}, function (data) {
                    $("#current_cart").hide('explode', function(){
                        $("#current_cart tbody tr").remove();
                        window.location="bought.jsp";
                    });
                });
            }
        </script>

        <html:base/>
    </head>
    <body>

    <center>
        <h1>Tienda</h1>
        <br/>
        <p><b>Sección:</b> 
            <select id="section" size="1" class="ui-widget">
                <option value="">-- None --</option>
                <logic:iterate name="sectionlist" id="each" type="org.magmax.master.project.ui.shop.SectionForm">
                    <option><bean:write name="each" property="name"/></option>
                </logic:iterate>
            </select>
        </p>

        <br/>
        <div id="pricelist">
        </div>
        <br/>
        <h2>Compra actual</h2>
        <div>
            <table class="ui-widget-content" id="current_cart">
                <thead >
                    <tr class="ui-widget-header">
                        <td></td>
                        <td>Referencia</td>
                        <td>Producto</td>
                        <td>Precio</td>
                        <td>Descripción</td>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <br/>
            <a href="javascript:void(formalize())" class="button" style="display:none" id="formalization">Formalizar compra</a>
        </div>
    </center>
    <html:link forward="welcome" styleClass="button" >Volver</html:link>

    <logic:equal scope="session" 
    
</body>
</html:html>
