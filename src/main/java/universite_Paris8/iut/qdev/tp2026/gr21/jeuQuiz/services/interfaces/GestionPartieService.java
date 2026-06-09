package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.interfaces;

import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.ElementDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.utils.exceptions.AucunJoueurException;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.utils.exceptions.AucuneQuestionException;

public interface GestionPartieService {

    /**
     * Détermine les éléments disponibles (questionnaires et joueurs) pour démarrer une partie.
     * * @return ElementDispoDTO contenant les listes de questionnaires et de joueurs valides.
     * @throws AucuneQuestionException si le SI Questionnaire ne renvoie aucune donnée valide.
     * @throws AucunJoueurException si le SI Joueur ne renvoie aucun joueur disponible.
     */
    ElementDispoDTO determinerElementsDispoPourPartie() throws AucuneQuestionException, AucunJoueurException;

    // simuler les retours d'appels de joueur et qst de plusiseurs test (liste vide, liste pleine pour les 2)

}