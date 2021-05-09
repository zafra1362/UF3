package TCP;

import java.io.Serializable;
import java.util.List;

public class Lista implements Serializable {
    private static final long serialVersionUID = 2L;
    private String nom;
    private List<Integer> numberList;

    public Lista(String nom, List<Integer> numberList) {
        this.nom = nom;
        this.numberList = numberList;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Integer> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Integer> numberList) {
        this.numberList = numberList;
    }
}
