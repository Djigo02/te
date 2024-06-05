<%@ page import="sn.dev.crudjee.web.ProduitModel" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.dev.crudjee.entity.Produit" %>
<%@ page import="sn.dev.crudjee.dao.IProduitDao" %>
<%@ page import="sn.dev.crudjee.dao.ProduitImplDao" %><%--
  Created by IntelliJ IDEA.
  User: Abdou Fatah Ndiaye
  Date: 29/05/2024
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    IProduitDao produitDao = new ProduitImplDao();
    List<Produit> produits = produitDao.list();
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5 col-md-5 col-xm-12 col-sm-6 col-md-offset-3">
    <div class="card">
        <div class="card-header center">
            Simulatuion de credit
        </div>
        <div class="card-body">
            <form action="ProduitServlet.do" method="post">
                <div class="form-group">
                    <label class="control-label">Designation </label>
                    <input type="text" class="form-control" name="designation">
                </div>
                <br>
                <div class="form-group">
                    <label class="control-label">Prix </label>
                    <input type="text" class="form-control" name="prix">
                </div>
                <br>
                <div class="form-group">
                    <label class="control-label">Quantite </label>
                    <input type="text" class="form-control" name="quantite">
                </div>
                <br>
                <div>
                    <button type="submit" class="btn btn-outline-success">Ajouter</button>
                </div>
            </form>
        </div>
    </div>
</div>
    <div class="container mt-5 col-md-5 col-xm-12 col-sm-6 col-md-offset-3">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Designation</th>
                <th scope="col">Prix</th>
                <th scope="col">Quantite</th>
                <th scope="col">Options</th>
            </tr>
            </thead>
            <tbody>
            <%

                    for (Produit produit : produits)
                    {
            %>
            <tr>
                <th scope="row"><%= produit.getId() %></th>
                <td><%= produit.getDesignation() %></td>
                <td><%= produit.getPrix() %></td>
                <td><%= produit.getQuantite() %></td>
                <td>
                    <button class="btn btn-outline-secondary">Update</button>
                    <button class="btn btn-outline-danger">Delete</button>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</body>
</html>
