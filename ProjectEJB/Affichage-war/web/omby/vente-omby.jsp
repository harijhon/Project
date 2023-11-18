<%-- 
    Document   : vente-omby
    Created on : 21 oct. 2023, 09:36:20
    Author     : ismae
--%>

<%@page import="ombyEntity.Localisation"%>
<%@page import="ombyEntity.Olona"%>
<%@page import="ombyEntity.Omby"%>

<%@ include file="../template/Top.jsp" %>


<div class="card-body">
    <form action="<%= request.getContextPath() %>/Omby" method="post" >
        <input type="hidden" name="action" value='vente' >
        <div class="mb-3">
            <%
                Olona[] another = (Olona[]) request.getAttribute("another");
            %>
            <label for="Select" class="form-label">Choisissez le nom de l'acheteur</label>
            <select id="Select" class="form-select" name="acheteur" >
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
                <option value="<%= lieu.getIdLoc() %>" ><%= lieu.getNom() %></option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Date de vente</label>
            <input type="date" class="form-control" id="exampleInputPassword1" name="date">
        </div>

        <table class="table" >
            <thead>
            <th>Id</th>
            <th>Nom</th>
            <th>Poids</th>
            </thead>
            <tbody>
                <%
                    Omby[] ombyTab = (Omby[]) request.getAttribute("omby");
                    for (Omby omby : ombyTab) {%>
                <tr>
                    <td> <input type="checkbox" class="form-check-input" name="idOmby[]" value="<%=omby.getIdOmby()%>" > </td>
                    <td><%=omby.getIdOmby()%></td>
                    <td><%=omby.getNom()%></td>
                    <td><%=omby.getPoids()%></td>
                </tr>
                <% }
                %>
            </tbody>
        </table>


        <input type="submit" class="btn btn-primary" value='Vendre' >
    </form>



</div>




<%@ include file="../template/Bottom.jsp" %>
