<%-- 
    Document   : index
    Created on : 20-jun-2011, 7:34:58
    Author     : Miguel Angel Garcia <miguelangel.garcia@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="registro" method="post">
            <center>
                <table>
                    <tr>
                        <td>Login:</td><td><input type="text"
                                                  name="login"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td><td><input type="text"
                                                     name="password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit"
                                               value="Entrar"></td>
                    </tr>
                </table>
            </center>
        </form>
    </body>

</html>
