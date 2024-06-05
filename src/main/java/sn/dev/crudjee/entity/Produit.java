package sn.dev.crudjee.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Produit {
    private int id;
    private String designation;
    private Double prix;
    private int quantite;

    public Produit(int id) {
        this.id=id;
    }
}
