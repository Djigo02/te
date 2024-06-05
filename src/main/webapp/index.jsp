<%@ page import="sn.dev.crudjee.web.ProduitModel" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.dev.crudjee.entity.Produit" %>
<%@ page import="sn.dev.crudjee.dao.IProduitDao" %>
<%@ page import="sn.dev.crudjee.dao.ProduitImplDao" %>
<%--
  User: Mouhamad DJIGO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    IProduitDao produitDao = new ProduitImplDao();
    List<Produit> produits = produitDao.list();
%>
<html>
<head>
    <title>CRUD JEE</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>

<%--MODAL--%>

<form action="AjoutProduitServlet.do" method="post">
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Ajouter un produit</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
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
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                <button type="submit" class="btn btn-success">Ajouter</button>
            </div>
        </div>
    </div>
</div>

<%----%>
</form>

<div class="container mt-5 col-md-5 col-xm-12 col-sm-6 col-md-offset-3">
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-success float-end mt-4" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Ajouter un produit
    </button>
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
                <a href="ProduitServlet.do?action=update&id=<%= produit.getId() %>" class="btn btn-warning">Modifier</a>
                <a href="ProduitServlet.do?action=delete&id=<%= produit.getId() %>" class="btn btn-danger">Supprimer</a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
