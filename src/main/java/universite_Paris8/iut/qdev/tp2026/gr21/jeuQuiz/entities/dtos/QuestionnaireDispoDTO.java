package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos;

import java.util.Objects;

public class QuestionnaireDispoDTO {
    private int idQuestionnair;
    private int nbQstSimple;
    private int nbQstIntermediaire;
    private int nbQstExpert;
    private String langue;

    public QuestionnaireDispoDTO() {}

    // Getters et Setters
    public int getIdQuestionnair() { return idQuestionnair; }
    public void setIdQuestionnair(int idQuestionnair) { this.idQuestionnair = idQuestionnair; }
    public int getNbQstSimple() { return nbQstSimple; }
    public void setNbQstSimple(int nbQstSimple) { this.nbQstSimple = nbQstSimple; }
    public int getNbQstIntermediaire() { return nbQstIntermediaire; }
    public void setNbQstIntermediaire(int nbQstIntermediaire) { this.nbQstIntermediaire = nbQstIntermediaire; }
    public int getNbQstExpert() { return nbQstExpert; }
    public void setNbQstExpert(int nbQstExpert) { this.nbQstExpert = nbQstExpert; }
    public String getLangue() { return langue; }
    public void setLangue(String langue) { this.langue = langue; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionnaireDispoDTO constr = (QuestionnaireDispoDTO) o;
        return idQuestionnair == constr.idQuestionnair &&
                nbQstSimple == constr.nbQstSimple &&
                nbQstIntermediaire == constr.nbQstIntermediaire &&
                nbQstExpert == constr.nbQstExpert &&
                Objects.equals(langue, constr.langue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuestionnair, nbQstSimple, nbQstIntermediaire, nbQstExpert, langue);
    }

    @Override
    public String toString() {
        return "QuestionnaireDispoDTO{" +
                "idQuestionnair=" + idQuestionnair +
                ", nbQstSimple=" + nbQstSimple +
                ", nbQstIntermediaire=" + nbQstIntermediaire +
                ", nbQstExpert=" + nbQstExpert +
                ", langue='" + langue + '\'' +
                '}';
    }
}
