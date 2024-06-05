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
public class ProduitServlet extends HttpServlet {
    private IProduitDao metier;


    @Override
    public void init() throws ServletException {
        metier = new ProduitImplDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String action = request.getParameter("action");
        if (action != null && action.equals("delete"))
        {
            int produitId = Integer.parseInt(request.getParameter("id"));
            Produit produit = new Produit();
            produit.setId(produitId);
            metier.delete(produit);

            response.sendRedirect("index.jsp");
        }else if (action != null && action.equals("update"))
        {
            int produitId = Integer.parseInt(request.getParameter("id"));
            Produit produit = metier.produitById(produitId);

            request.setAttribute("produit", produit);
            request.getRequestDispatcher("update.jsp").forward(request,response);
        }
        else
        {
            List<Produit> produits = metier.list();
            ProduitModel model = new ProduitModel();
            model.setProduits(produits);
            request.setAttribute("produitModel",model);
            request.getRequestDispatcher("produit.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String designation = request.getParameter("designation");
        Double prix = Double.parseDouble(request.getParameter("prix"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        int id = Integer.parseInt(request.getParameter("id"));
        Produit produit = new Produit();

//        if (action != null && action.equals("update")){
            Produit p = metier.produitById(id);
            if (p != null)
            {
                p.setDesignation(designation);
                p.setPrix(prix);
                p.setQuantite(quantite);

                int res= metier.update(p);
                if (res > 0)
                {
                    response.sendRedirect("index.jsp");
                }else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Le Produit est introuvable !!!");
                }
            }else
            {
                out.println("Pas de produit trouve !!!");
            }
        /*}
        else
        {
            produit.setDesignation(designation);
            produit.setPrix(prix);
            produit.setQuantite(quantite);

            metier.save(produit);

            response.sendRedirect("success.jsp");
        }*/
    }
}
