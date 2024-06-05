package sn.dev.crudjee.dao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.crudjee.entity.Produit;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitImplDao implements IProduitDao{
    SingletonConnection db = new SingletonConnection();
    ResultSet rs;
    @Override
    public Produit save(Produit p) {
        String sql = "INSERT INTO produit (designation, prix, quantite) VALUES (?,?,?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1,p.getDesignation());
            db.getPstm().setDouble(2,p.getPrix());
            db.getPstm().setInt(3,p.getQuantite());

            int res = db.executeMaj();
            if (res > 0)
            {
                rs = db.getPstm().getGeneratedKeys();
                if (rs.next())
                {
                    p.setId(rs.getInt(1));
                }
            }
            db.clossConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Produit> list() {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit ORDER BY designation ASC";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next())
            {
                Produit p = new Produit(
                        rs.getInt("id"),
                        rs.getString("designation"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite")
                );
                produits.add(p);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            db.clossConnection();
        }
        return produits;
    }

    @Override
    public List<Produit> produitByMc(String mc) {
        return null;
    }

    @Override
    public Produit produitById(int id) {
        Produit produit = null;
        String sql = "SELECT * FROM produit WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            rs = db.executeSelect();
            if (rs.next())
            {
                produit = new Produit(
                        rs.getInt("id"),
                        rs.getString("designation"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite")
                );
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            db.clossConnection();
        }
        return produit;
    }

    @Override
    public int update(Produit p) {
        int ligneSel = 0;
        String sql = "UPDATE produit SET designation=?,prix=?,quantite=? WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1,p.getDesignation());
            db.getPstm().setDouble(2,p.getPrix());
            db.getPstm().setInt(3,p.getQuantite());
            db.getPstm().setInt(4,p.getId());
            ligneSel = db.executeMaj();

        }catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            db.clossConnection();
        }
        return ligneSel;
    }

    @Override
    public int delete(Produit p) {
        int ligneSelect = 0;
        String sql = "DELETE FROM produit WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,p.getId());
            ligneSelect = db.executeMaj();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return ligneSelect;
    }
}
