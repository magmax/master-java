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
                $(".button").button();
                
                var dates = $("#from, #to" ).datepicker({
                    dateFormat: 'dd-mm-yy', 
                    firstDay: 1,
                    defaultDate: "+1w",
                    changeMonth: true,
                    onSelect: function( selectedDate ) {
                        var option = this.id == "from" ? "minDate" : "maxDate", 
                        instance = $( this ).data("datepicker"),
                        date = $.datepicker.parseDate(instance.settings.dateFormat ||$.datepicker._defaults.dateFormat, selectedDate, instance.settings );
                        dates.not( this ).datepicker( "option", option, date );
                    }
                });
                
                $("#filter").click(function(){
                    $("#invoices").slideUp( function(){
                        $.post("invoicelist.do", {"from":dates[0].value, "to":dates[1].value}, function (data) {
                            $("#invoices").html(data).slideDown();
                        });
                    });
                });
            });
        </script>
        <html:base/>
    </head>
    <body>

    <center>
        <h1>Facturación</h1>
        <br/>

        <div>
            <label for="from">From</label>
            <input type="text" id="from" name="from"/>
            <label for="to">to</label>
            <input type="text" id="to" name="to"/>
            <br/>
            <button class="button" id="filter">Buscar</button>
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
