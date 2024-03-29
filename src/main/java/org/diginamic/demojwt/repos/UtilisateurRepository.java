package org.diginamic.demojwt.repos;

import java.util.Optional;

import org.diginamic.demojwt.beans.Utilisateur;
import org.springframework.data.repository.CrudRepository;

/** Repository pour les opérations de persistance concernant la classe Utilisateur.
 * @author RichardBONNAMY
 *
 */
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

	/** Recherche un utilisateur par email (unique)
	 * @param email email 
	 * @return {@link Optional}
	 */
	Optional<Utilisateur> findByEmail(String email);
}