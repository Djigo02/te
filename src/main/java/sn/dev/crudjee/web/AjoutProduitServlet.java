package sn.dev.crudjee.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.crudjee.dao.IProduitDao;
import sn.dev.crudjee.dao.ProduitImplDao;
import sn.dev.crudjee.entity.Produit;

import java.io.IOException;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

@WebServlet(name = "ps", urlPatterns = {"*.do"})
public class AjoutProduitServlet extends HttpServlet {
    private IProduitDao metier;


    @Override
    public void init() throws ServletException {
        metier = new ProduitImplDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String designation=request.getParameter("designation");
        double prix=Double.parseDouble(request.getParameter("prix"));
        int quantite=Integer.parseInt(request.getParameter("quantite"));
        Produit produit =new Produit();
        produit.setDesignation(designation);
        produit.setPrix(prix);
        produit.setQuantite(quantite);
        try {
            metier.save(produit);
            System.out.println("Ajout reussi");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        response.sendRedirect("/");
//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
