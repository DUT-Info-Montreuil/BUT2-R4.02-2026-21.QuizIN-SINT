package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos;

import java.util.List;
import java.util.Objects;

public class ElementDispoDTO {
    private List<JoueurDispoDTO> listJoueurDispo;
    private List<QuestionnaireDispoDTO> listQuestionnaire;

    public ElementDispoDTO() {}

    // Getters et Setters
    public List<JoueurDispoDTO> getListJoueurDispo() { return listJoueurDispo; }
    public void setListJoueurDispo(List<JoueurDispoDTO> listJoueurDispo) { this.listJoueurDispo = listJoueurDispo; }
    public List<QuestionnaireDispoDTO> getListQuestionnaire() { return listQuestionnaire; }
    public void setListQuestionnaire(List<QuestionnaireDispoDTO> listQuestionnaire) { this.listQuestionnaire = listQuestionnaire; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementDispoDTO constr = (ElementDispoDTO) o;
        return Objects.equals(listJoueurDispo, constr.listJoueurDispo) &&
                Objects.equals(listQuestionnaire, constr.listQuestionnaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listJoueurDispo, listQuestionnaire);
    }

    @Override
    public String toString() {
        return "ElementDispoDTO{" +
                "listJoueurDispo=" + listJoueurDispo +
                ", listQuestionnaire=" + listQuestionnaire +
                '}';
    }
}
