<%-- 
    Document   : list-plainte
    Created on : Oct 22, 2023, 10:42:37 PM
    Author     : RazOnTheFloor
--%>

<%@page import="myUtil.DateConvertor"%>
<%@page import="warEntity.Plainte"%>
<%@ include file= "../template/Top.jsp" %>

<a href="<%= request.getContextPath() %>/Plainte?action=creation" class="btn btn-primary" >Creer</a>

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
            Plainte[] plaintes =(Plainte[]) request.getAttribute("plainte");
            for(Plainte p: plaintes ){ %>
            <tr>
                <td><%=p.getIdPlainte() %></td>
                <td><%=p.getIdOmby() %></td>
                
                <% if(p.getLieuPlainte() != null){%>
                <td><%=p.getLieuPlainte().toString() %></td>
                <% }else {out.println("<td>Null</td>");} %>
                
                
                <% if(p.getDatePlainte() != null){%>
                <td><%=DateConvertor.getAnnotationSimple(p.getDatePlainte()) %></td>
                <% }else {out.println("<td>Null</td>");} %>
                
                <% if(p.getLieuResolution() != null){%>
                <td><%=p.getLieuResolution().toString() %></td>
                <% }else {out.println("<td>Null</td>");} %>
                
                <% if(p.getDatePlainte() != null){%>
                <td><%=DateConvertor.getAnnotationSimple(p.getDateResolution()) %></td>
                <% }else {out.println("<td>Null</td>");} %>
                <td><a href="<%= request.getContextPath() %>/Plainte?action=update&idPlainte=<%=p.getIdPlainte() %>" class="btn btn-primary" >edit</a></td>
                <td><a href="<%= request.getContextPath() %>/Plainte?action=fiche&idPlainte=<%=p.getIdPlainte() %>" class="btn btn-warning" >details</a></td>
                <td><a href="<%= request.getContextPath() %>/Plainte?action=delete&idPlainte=<%=p.getIdPlainte() %>" class="btn btn-danger" >delete</a></td>
            </tr>
            <% } 
        %>
    </tbody>
</table>

<%@ include file="../template/Bottom.jsp" %>