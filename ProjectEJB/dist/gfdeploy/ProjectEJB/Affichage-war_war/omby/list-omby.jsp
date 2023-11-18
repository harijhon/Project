<%-- 
    Document   : home
    Created on : 21 oct. 2023, 00:21:38
    Author     : ismae
--%>

<%@page import="ombyEntity.Omby"%>
<%@ include file= "../template/Top.jsp" %>

<a href="<%= request.getContextPath() %>/Omby?action=vente" class="btn btn-primary" >Vendre</a><br>
<a href="<%= request.getContextPath() %>/Omby?action=creation" class="btn btn-primary" >Creer</a>
<br>
<table class="table" >
    <thead>
    <th>Id</th>
    <th>Nom</th>
    <th>Poids</th>
    </thead>
    <tbody>
        <% 
            Omby[] ombyTab =(Omby[]) request.getAttribute("omby");
            for(Omby omby: ombyTab ){ %>
            <tr>
                <td><%=omby.getIdOmby()  %></td>
                <td><%=omby.getNom()  %></td>
                <td><%=omby.getPoids()  %></td>
            </tr>
            <% } 
        %>
    </tbody>
</table>



<%@ include file="../template/Bottom.jsp" %>
