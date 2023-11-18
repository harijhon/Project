<%-- 
    Document   : update-mutation
    Created on : Oct 24, 2023, 7:21:49 PM
    Author     : RazOnTheFloor
--%>

<%@page import="myUtil.DateConvertor"%>
<%@page import="varotraEntity.Mutation"%>
<%@page import="ombyEntity.Localisation"%>
<%@page import="ombyEntity.Olona"%>
<%@page import="ombyEntity.Omby"%>
<%@ include file= "../template/Top.jsp" %>

<% Mutation mutation = (Mutation) request.getAttribute("mutation");
    if (mutation == null) {
        request.getRequestDispatcher("mutation/list-mutation.jsp").forward(request, response);
    } %>
    <form method="post" action="<%= request.getContextPath() %>/Mutation" >
    <input type="hidden" name="action" value='update' >
    <input type="hidden" name="id" value='<%= mutation.getIdMutation() %>' >
    <%
        Olona[] another = (Olona[]) request.getAttribute("another");
        Omby[] omby = (Omby[]) request.getAttribute("omby");
    %>
    
    <div class="mb-3">
        <label for="Select" class="form-label">Choisissez le boeuf</label>
        <select id="Select" class="form-select" name="omby" >
            <% for (Omby o : omby) {
                    if (mutation.getIdOmby().equals(o.getIdOmby() )) {%> 
            <option value="<%= o.getIdOmby()%>" selected ><%= o.getNom()%></option> 
            <%}%>
            <option value="<%=  o.getIdOmby()%>" ><%= o.getNom()%></option>
            <% } %>
        </select>
    </div>
    
    <div class="mb-3">
        <label for="Select" class="form-label">Choisissez le nom du vendeur</label>
        <select id="Select" class="form-select" name="vendeur" >
            <% for (Olona olona : another) {
                    if (mutation.getIdMpivarotra().equals(olona.getIdOlona())) {%> 
            <option value="<%= olona.getIdOlona()%>" selected ><%= olona.getNom()%></option> 
            <%}%>
            <option value="<%= olona.getIdOlona()%>" ><%= olona.getNom()%></option>
            <% } %>
        </select>
    </div>

    <div class="mb-3">
        <label for="Select" class="form-label">Choisissez le nom de l'acheteur</label>
        <select id="Select" class="form-select" name="acheteur" >
            <% for (Olona olona : another) {
                    if (mutation.getIdMpividy().equals(olona.getIdOlona())) {%> 
            <option value="<%= olona.getIdOlona()%>" selected ><%= olona.getNom()%></option> 
            <%}%>
            <option value="<%= olona.getIdOlona()%>" ><%= olona.getNom()%></option>
            <% } %>
        </select>
    </div>

    <div class="mb-3">
        <%
            Localisation[] lieus = (Localisation[]) request.getAttribute("lieu");
        %>
        <label for="Select" class="form-label">lieu de vente</label>
        <select id="Select" class="form-select" name="lieuVente" >
            <% for (Localisation lieu : lieus) {%>
            <% if (lieu.getIdLoc() == mutation.getLieu() ) {%> 
            <option value="<%= lieu.getIdLoc()%>" selected="" ><%= lieu.getNom()%></option>  
            <% } else {%>
            <option value="<%= lieu.getIdLoc()%>" ><%= lieu.getNom()%></option>
            <% }
                }%>
        </select>
    </div>

    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Date de vente</label>
        <input type="date" class="form-control" id="exampleInputPassword1" name="date" value="<%=DateConvertor.getAnnotation(mutation.getDateMutation()) %>">
    </div>
    <input type="submit" value="update" class="btn btn-primary" >
</form>



<%@ include file="../template/Bottom.jsp" %>