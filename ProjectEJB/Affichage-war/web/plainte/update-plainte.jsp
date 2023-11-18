<%@page import="myUtil.DateConvertor"%>
<%@page import="warEntity.Plainte"%>
<%@page import="ombyEntity.Omby"%>
<%@page import="ombyEntity.Localisation"%>
<%@ include file= "../template/Top.jsp" %>
<%
    Plainte plainte = (Plainte) request.getAttribute("plainte");
    Omby[] omby = (Omby[]) request.getAttribute("omby");
%>
<form id="createPlainteForm" action="http://localhost:58974/plaintes/Update/<%= plainte.getIdPlainte()%>" method="post">
    <div class="mb-3">
    </div>
    <div class="mb-3">
        <input type="hidden" name="idPlainte" id="idPlainte" value="<%= plainte.getIdPlainte()%>" />

        <label for="Select" class="form-label">Omby</label>
        <select id="Select" class="form-select" name="idOmby" >
            <% for (Omby o : omby) {%>
            <% if (o.getIdOmby() == plainte.getIdOmby()) {%> 
            <option value="<%= o.getIdOmby()%>" selected="" ><%= o.getNom()%></option>  
            <% } else {%>
            <option value="<%= o.getIdOmby()%>" ><%= o.getNom()%></option>
            <% }
                }%>
        </select> 
    </div>

    <div class="mb-3">
        <%
            Localisation[] lieus = (Localisation[]) request.getAttribute("lieu");
        %>
        <label for="Select" class="form-label">lieu de plainte</label>
        <select id="Select" class="form-select" name="lieuPlainte" >
            <% for (Localisation lieu : lieus) {%>
            <% if (lieu.getIdLoc() == plainte.getLieuPlainte()) {%> 
            <option value="<%= lieu.getIdLoc()%>" selected="" ><%= lieu.getNom()%></option>  
            <% } else {%>
            <option value="<%= lieu.getIdLoc()%>" ><%= lieu.getNom()%></option>
            <% }
                }%>
        </select>
    </div>

    <div class="mb-3">
        <label for="datePlainte">Date Plainte:</label>
        <input type="date" name="datePlainte" id="datePlainte" value="<%= DateConvertor.getAnnotation(plainte.getDatePlainte()) %>" class="form-control" />
    </div>

    <div class="mb-3">
        <label for="Select" class="form-label">lieu de resolution</label>
        <select id="Select" class="form-select" name="lieuResolution" >
            <option value="null" selected="" >inconnue</option>
            <% for (Localisation lieu : lieus) {%>
            <% if (lieu.getIdLoc() == plainte.getLieuResolution()) {%> 
            <option value="<%= lieu.getIdLoc()%>" selected="" ><%= lieu.getNom()%></option>  
            <% } else {%>
            <option value="<%= lieu.getIdLoc()%>" ><%= lieu.getNom()%></option>
            <% }
                }%>
        </select>
    </div>

    <div class="mb-3">
        <label for="dateResolution" >Date Résolution:</label>
        <input type="date" name="dateResolution" id="dateResolution" value="<%= DateConvertor.getAnnotation(plainte.getDateResolution()) %>"  class="form-control"/>
    </div>





    <input class="btn btn-primary" type="submit" value="Créer Plainte" />
</form>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    /*$(document).ready(function () {
    $("#createPlainteForm").submit(function (e) {
        e.preventDefault(); // Empêche la soumission du formulaire standard

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/Affichage-war/Omby?action=removeAssignment&id=<%= plainte.getIdOmby() %>",
            success: function (data, status, xhr) {
                
                if (xhr.status === 200) {
                    console.log('tonga wto');
                    // Si le code de statut est 200, soumettez le formulaire standard
                    $("#createPlainteForm")[0].submit();
                } else {
                    alert("Erreur lors de la suppression de l'assignation.");
                }
            },
            error: function () {
                alert("Erreur lors de la requête vers 'RemoveAssignment'.");
            }
        });
    });
});
*/

</script>

<%@ include file="../template/Bottom.jsp" %>