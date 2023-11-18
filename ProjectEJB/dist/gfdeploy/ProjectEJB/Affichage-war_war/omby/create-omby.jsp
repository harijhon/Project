<%-- 
    Document   : create-omby
    Created on : Oct 22, 2023, 4:26:36 PM
    Author     : RazOnTheFloor
--%>

<%@page import="ombyEntity.Localisation"%>
<%@page import="ombyEntity.Olona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../template/Top.jsp" %>s

<form action="<%= request.getContextPath() %>/Omby" method="POST" >
    <input type="hidden" value="create" name="action">
    <div class="mb-3">
        <label for="nom" class="form-label">Nom</label>
        <input type="text" class="form-control" name="nom" id="nom">
    </div>
    <div class="mb-3">
        <label for="nom" class="form-label">Poid</label>
        <input type="text" class="form-control" name="nom" id="nom">
    </div>
    <div class="mb-3">
        <%
            Olona[] another = (Olona[]) request.getAttribute("another");
        %>
        <label for="Select" class="form-label">Choisissez le nom du proprio</label>
        <select id="Select" class="form-select" name="proprio" >
            <% for (Olona olona : another) {%>
            <option value="<%= olona.getIdOlona()%>" ><%= olona.getNom()%></option>
            <% } %>
        </select>
    </div>

    <div class="mb-3">
        <%
            Localisation[] lieus = (Localisation[]) request.getAttribute("lieu");
        %>
        <label for="Select" class="form-label">Choisissez le lieu</label>
        <select id="Select" class="form-select" name="lieu" >
            <% for (Localisation lieu : lieus) {%>
            <option value="<%= lieu.getIdLoc()%>" ><%= lieu.getNom()%></option>
            <% }%>
        </select>
    </div>
        <input type="submit" value="creer" class="btn btn-primary" >
</form>

<%@ include file="../template/Bottom.jsp" %>