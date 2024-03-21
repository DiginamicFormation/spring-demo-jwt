package org.diginamic.demojwt.repos;

import java.util.Optional;

import org.diginamic.demojwt.beans.Utilisateur;
import org.springframework.data.repository.CrudRepository;

/**
 * @author RichardBONNAMY
 *
 */
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

    // Vous pouvez définir des méthodes spécifiques de requête ici
	Optional<Utilisateur> findByEmail(String email);
}