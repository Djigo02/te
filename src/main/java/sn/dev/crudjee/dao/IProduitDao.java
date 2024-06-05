package sn.dev.crudjee.dao;

import sn.dev.crudjee.entity.Produit;

import java.util.List;

public interface IProduitDao {

    public Produit save(Produit p);
    public List<Produit> list();
    public List<Produit> produitByMc(String mc);
    public Produit produitById(int id);
    public int update(Produit p);
    public int delete(Produit p);
}
