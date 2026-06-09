package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos;

import java.util.Objects;

public class JoueurDispoDTO {
    private int idJoueur;
    private String prenom;
    private String pseudo;

    public JoueurDispoDTO() {}

    // Getters et Setters
    public int getIdJoueur() { return idJoueur; }
    public void setIdJoueur(int idJoueur) { this.idJoueur = idJoueur; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getPseudo() { return pseudo; }
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoueurDispoDTO constr = (JoueurDispoDTO) o;
        return idJoueur == constr.idJoueur &&
                Objects.equals(prenom, constr.prenom) &&
                Objects.equals(pseudo, constr.pseudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJoueur, prenom, pseudo);
    }

    @Override
    public String toString() {
        return "JoueurDispoDTO{" +
                "idJoueur=" + idJoueur +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                '}';
    }
}