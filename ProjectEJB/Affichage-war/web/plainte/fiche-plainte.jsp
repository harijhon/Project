<%-- 
    Document   : list-plainte
    Created on : Oct 22, 2023, 10:42:37 PM
    Author     : RazOnTheFloor
--%>

<%@page import="warEntity.Plainte"%>
<%@ include file= "../template/Top.jsp" %>
<br>
<table class="table" >
    <thead>
    <th>IdPlainte</th>
    <th>IdOmby</th>
    <th>lieuPlainte</th>
    <th>datePlainte</th>
    <th>lieuResolution</th>
    <th>dateResolution</th>
    </thead>
    <tbody>
        <% 
            Plainte plainte =(Plainte) request.getAttribute("plainte");%>
            <tr>
                <td><%=plainte.getIdPlainte() %></td>
                <td><%=plainte.getIdOmby() %></td>
                <td><%=plainte.getLieuPlainte() %></td>
                <% if(plainte.getDatePlainte() != null){%>
                <td><%=plainte.getDatePlainte().toString() %></td>
                <% }else {out.println("<td>Null</td>");} %>
                <td><%=plainte.getLieuResolution() %></td>
                <% if(plainte.getDatePlainte() != null){%>
                <td><%=plainte.getDateResolution().toString() %></td>
                <% }else {out.println("<td>Null</td>");} %>
            </tr>
    </tbody>
</table>

<%@ include file="../template/Bottom.jsp" %>
