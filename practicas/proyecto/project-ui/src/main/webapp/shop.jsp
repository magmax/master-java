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
        <html:base/>
    </head>
    <body>

    <center>
        <h1>Tienda</h1>
        <br/>
        <p><b>Sección:</b> 
            <select name="section" size="1">
                <option value="">-- None --</option>
                <logic:iterate name="sectionlist" id="each" type="org.magmax.master.project.ui.shop.SectionForm">
                    <option><bean:write name="each" property="name"/></option>
                </logic:iterate>
            </select>
        </p>

        <br/>
        <br/>
    </center>
    <html:link forward="welcome">Volver</html:link>
    </body>
</html:html>
