<%@page import="ombyEntity.Omby"%>
<%@page import="ombyEntity.Localisation"%>
<%@ include file= "../template/Top.jsp" %>
<form id="createPlainteForm" action="http://localhost:58974/plaintes/Create" method="post">
    <div class="mb-3">
        <input type="hidden" name="idPlainte" id="idPlainte" />
    </div>
    <div class="mb-3">
        <%
            Omby[] omby = (Omby[]) request.getAttribute("omby");
        %>
        <label for="Select" class="form-label">Omby</label>
        <select id="Select" class="form-select" name="idOmby" >
            <% for (Omby o : omby) {%>
            <option value="<%= o.getIdOmby() %>" ><%= o.getNom()%></option>
            <% }%>
        </select>
    </div>
        
    <div class="mb-3">
        <%
            Localisation[] lieus = (Localisation[]) request.getAttribute("lieu");
        %>
        <label for="Select" class="form-label">lieu de plainte</label>
        <select id="Select" class="form-select" name="lieuPlainte" >
            <% for (Localisation lieu : lieus) {%>
            <option value="<%= lieu.getIdLoc()%>" ><%= lieu.getNom()%></option>
            <% }%>
        </select>
    </div>
        
    <div class="mb-3">
        <label for="datePlainte">Date Plainte:</label>
        <input type="date" name="datePlainte" id="datePlainte" />
    </div>
        
        <div class="mb-3">
        <label for="Select" class="form-label">lieu de Resolution</label>
        <select id="Select" class="form-select" name="lieuResolution" >
            <% for (Localisation lieu : lieus) {%>
            <option value="<%= lieu.getIdLoc()%>" ><%= lieu.getNom()%></option>
            <% }%>
        </select>
    </div>
    <div class="mb-3">
        <label for="datePlainte">Date Plainte:</label>
        <input type="date" name="dateResolution" id="dateResolution" />
    </div>
        
    <input type="submit" value="Créer Plainte" />
</form>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    /*$(document).ready(function () {
     $("#createPlainteForm").submit(function (e) {
     e.preventDefault(); // Empêche la soumission du formulaire standard
     var formData = $(this).serialize(); // Sérialisez les données du formulaire
     
     $.ajax({
     type: "POST",
     url: "http://localhost:58974/plaintes/Create", // URL de votre action ASP.NET MVC
     data: formData,
     success: function (data) {
     // Gérez la réponse de l'action ASP.NET MVC ici
     alert("Plainte créée avec succès !");
     },
     error: function () {
     alert("Erreur lors de la création de la plainte.");
     }
     });
     });
     });*/
</script>

<%@ include file="../template/Bottom.jsp" %>