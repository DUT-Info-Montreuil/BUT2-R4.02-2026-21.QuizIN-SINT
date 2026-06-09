package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.interfaces;

import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.ElementDispoDTO;

public interface GestionPartieService {

    /**
     * Détermine les éléments disponibles (questionnaires et joueurs) pour démarrer une partie.
     * * @return ElementDispoDTO contenant les listes de questionnaires et de joueurs valides.
     * @throws IllegalArgumentException si les questionnaires ou les joueurs sont invalides/vides.
     */
    ElementDispoDTO determinerElementsDispoPourPartie();

}

