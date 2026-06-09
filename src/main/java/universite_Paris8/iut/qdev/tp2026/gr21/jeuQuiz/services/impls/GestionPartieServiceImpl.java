package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.impls;

// --- IMPORTS DE VOTRE COUCHE SINT ---
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.ElementDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.JoueurDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.QuestionnaireDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.interfaces.GestionPartieService;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.utils.exceptions.AucunJoueurException;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.utils.exceptions.AucuneQuestionException;

// --- IMPORTS DU SME QUESTIONNAIRE (Attention: paris8 en minuscule) ---
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IQuestionnaireService;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.entities.dtos.QuestionnaireDTO;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.entities.dtos.QuestionDTO;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.utils.exceptions.ChargementImpossibleException;

// --- IMPORTS DU SME JOUEUR (Attention: Paris8 avec P majuscule selon votre code) ---
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IJoueurService;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.entities.dtos.JoueurDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.utils.exceptions.AucunJoueurEnregistreException;

import java.util.ArrayList;
import java.util.List;

public class GestionPartieServiceImpl implements GestionPartieService {

    private final IQuestionnaireService questionnaireService;
    private final IJoueurService joueurService;

    // Injection des véritables interfaces des deux autres équipes
    public GestionPartieServiceImpl(IQuestionnaireService questionnaireService, IJoueurService joueurService) throws ChargementImpossibleException, AucunJoueurEnregistreException {
        this.questionnaireService = questionnaireService;
        this.joueurService = joueurService;
    }

    @Override
    public ElementDispoDTO determinerElementsDispoPourPartie(){

        ElementDispoDTO elementDispoDTO = new ElementDispoDTO();

        // =========================================================
        // ETAPE 1 : APPEL DU SI METIER QUESTIONNAIRE
        // =========================================================

        List<QuestionnaireDTO> listQuestionnairesBruts = null;

        try {
            // Appel de la méthode de vos camarades
            listQuestionnairesBruts = questionnaireService.fournirListeQuestionnaires();
        } catch (ChargementImpossibleException e) {
            // Traduction de l'erreur SME vers l'erreur SINT
            throw new AucuneQuestionException("Il n'y a pas de question : charger un fichier de question");
        }

        if (listQuestionnairesBruts == null || listQuestionnairesBruts.isEmpty()) {
            throw new AucuneQuestionException("Il n'y a pas de question : charger un fichier de question");
        }

        // Mapping et Calcul des difficultés
        List<QuestionnaireDispoDTO> listQuestionnairesDispo = new ArrayList<>();

        for (QuestionnaireDTO q : listQuestionnairesBruts) {
            QuestionnaireDispoDTO qDTO = new QuestionnaireDispoDTO();
            qDTO.setIdQuestionnair(q.getIdQuestionnaire());

            // On compte les questions par difficulté en lisant la liste interne
            int nbSimple = 0, nbInter = 0, nbExpert = 0;
            String langue = "Inconnue";

            if (q.getListeQuestion() != null && !q.getListeQuestion().isEmpty()) {
                // On récupère la langue depuis la première question
                langue = q.getListeQuestion().get(0).getLangue().name();

                // On boucle sur les questions pour faire les totaux
                for (QuestionDTO question : q.getListeQuestion()) {
                    int diff = question.getDifficulte();
                    if (diff == 1) nbSimple++;
                    else if (diff == 2) nbInter++;
                    else if (diff == 3) nbExpert++;
                }
            }

            qDTO.setNbQstSimple(nbSimple);
            qDTO.setNbQstIntermediaire(nbInter);
            qDTO.setNbQstExpert(nbExpert);
            qDTO.setLangue(langue);

            listQuestionnairesDispo.add(qDTO);
        }

        elementDispoDTO.setListQuestionnaire(listQuestionnairesDispo);


        // =========================================================
        // ETAPE 2 : APPEL DU SI JOUEUR
        // =========================================================

        List<JoueurDTO> listJoueursBruts = null;
        try {
            listJoueursBruts = joueurService.listerJoueurs();
        } catch (AucunJoueurEnregistreException e) {
            throw new AucunJoueurException("Il n'y a aucun joueur : créer un joueur pour lancer une partie");
        }

        if (listJoueursBruts == null || listJoueursBruts.isEmpty()) {
            throw new AucunJoueurException("Il n'y a aucun joueur : créer un joueur pour lancer une partie");
        }

        List<JoueurDispoDTO> listJoueursDispo = new ArrayList<>();

        // Création d'un faux ID temporaire pour dépanner
        int fauxIdTemporaire = 1;

        for (JoueurDTO j : listJoueursBruts) {
            JoueurDispoDTO jDTO = new JoueurDispoDTO();

            // On utilise notre compteur comme ID en attendant que les collègues corrigent
            jDTO.setIdJoueur(fauxIdTemporaire);
            fauxIdTemporaire++; // Le prochain joueur aura l'ID 2, puis 3, etc.

            jDTO.setPrenom(j.getPrenom());
            jDTO.setPseudo(j.getPseudo());

            listJoueursDispo.add(jDTO);
        }

        elementDispoDTO.setListJoueurDispo(listJoueursDispo);



        // =========================================================
        // FIN DU FLUX
        // =========================================================

        return elementDispoDTO;
    }


}