<%-- 
    Document   : create-omby
    Created on : Oct 22, 2023, 4:26:36 PM
    Author     : RazOnTheFloor
--%>

<%@page import="myEntity.Resultat"%>
<%@page import="myEntity.District"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../template/Top.jsp" %>
    <input type="hidden" value="create" name="action">
    <div class="mb-3">
        <label for="nom" class="form-label">Nom Bureau vote</label>
        <input type="text" class="form-control" name="nom" id="nom">
    </div>
    <div class="mb-3">
       
        <label for="Select" class="form-label">Id district</label>
        <select id="Select" class="form-select" name="proprio" >
            <% for (District district : another) {%>
            <option value="<%= olona.getIddistrict()%>" ><%= olona.getIddistrict()%></option>
            <% } %>
        </select>
    </div>
        <div class="mb-3">
        <label for="nom" class="form-label">Nombre electeurs</label>
        <input type="number" class="form-control" name="nombre" id="nombre">
    </div>

    
        <input type="submit" value="creer" class="btn btn-primary" >
</form>

<%@ include file="../template/Bottom.jsp" %>