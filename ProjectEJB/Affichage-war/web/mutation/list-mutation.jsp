<%-- 
    Document   : list-mutation
    Created on : Oct 24, 2023, 7:21:38 PM
    Author     : RazOnTheFloor
--%>

<%@page import="myUtil.DateConvertor"%>
<%@page import="varotraEntity.Mutation"%>
<%@page import="ombyEntity.Omby"%>
<%@ include file= "../template/Top.jsp" %>

<br>
<table class="table" >
    <thead>
    <th>Id</th>
    <th>Omby</th>
    <th>Acheteur</th>
    <th>Vendeur</th>
    <th>Lieu</th>
    <th>Date</th>
    </thead>
    <tbody>
        <% 
            if(request.getAttribute("mutation")!=null){
            Mutation[] tab =(Mutation[]) request.getAttribute("mutation");
            for(Mutation m: tab ){ %>
            <tr>
                <td><%= m.getIdMutation()  %></td>
                <td><%= m.getIdOmby()%></td>
                <td><%= m.getIdMpividy()%></td>
                <td><%= m.getIdMpivarotra()%></td>
                <td><%= m.getLieu()%></td>
                <td><%=DateConvertor.getAnnotationSimple(m.getDateMutation())  %></td>
                <td><a href="<%= request.getContextPath() %>/Mutation?action=update&id=<%=m.getIdMutation() %>" class="btn btn-primary" >edit</a></td>
                <td><a href="<%= request.getContextPath() %>/Mutation?action=delete&id=<%=m.getIdMutation() %>" class="btn btn-danger" >delete</a></td>
            </tr>
            <% } 
            }
        %>
    </tbody>
</table>



<%@ include file="../template/Bottom.jsp" %>