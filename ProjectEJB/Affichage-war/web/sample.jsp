<%@page import="warEntity.Plainte"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Votre JSP</title>
    </head>
    <body>
        <h1>Données JSON :</h1>
        <pre><%= request.getAttribute("jsonData")%></pre>
        <% Plainte[] plaintes = (Plainte[]) request.getAttribute("data");
            if (plaintes != null) {
                for (Plainte p : plaintes) {
                    out.println(p.getIdPlainte()+"<br/>");
                }
            }
        %>
    </body>
</html>
