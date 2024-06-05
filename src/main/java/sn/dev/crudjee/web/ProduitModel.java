package sn.dev.crudjee.web;

import lombok.Data;
import sn.dev.crudjee.entity.Produit;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProduitModel {
    private List<Produit> produits = new ArrayList<>();
}
