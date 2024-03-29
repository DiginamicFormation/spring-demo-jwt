package org.diginamic.demojwt.repos;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.diginamic.demojwt.beans.Departement;
import org.diginamic.demojwt.beans.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour les opérations de persistance concernant la classe Ville.
 * 
 * @author RichardBONNAMY
 *
 */
public interface VilleRepository extends JpaRepository<Ville, Integer> {

	/**
	 * Recherche une ville par nom et département
	 * 
	 * @param nom  nom
	 * @param dept departement
	 * @return {@link Optional}
	 */
	Optional<Ville> findByNomAndDepartement(String nom, Departement dept);

	/**
	 * Recherche une ville par nom et département : autre exemple
	 * 
	 * @param nom  nom
	 * @param dept departement
	 * @return {@link Ville}
	 */
	Ville readByNomAndDepartement(String nom, Departement dept);

	/**
	 * Recherche une ville par nom et population
	 * 
	 * @param nom    nom
	 * @param nbHabs population
	 * @return {@link Ville}
	 */
	Ville getByNomAndPopulation(String nom, int nbHabs);

	/**
	 * Méthode qui fait la même chose que findAll()
	 * 
	 * @return List de Ville
	 */
	List<Ville> findBy(); // équivalent de findAll()

	/**
	 * Retoune toutes les villes triées dans l'ordre alphabétique
	 * 
	 * @return List de Ville
	 */
	List<Ville> findByOrderByNom();

	/**
	 * Retoune le Top 10 des villes les plus peuplées pour un code département
	 * donnée
	 * 
	 * @return List de Ville
	 */
	List<Ville> findTop10ByDepartementCodeOrderByPopulationDesc(String code);

	/**
	 * Réalise un comptage du nombre de villes par département et retourne les
	 * résultats dans une Map dont la clé est le département et la valeur est le nb
	 * de villes
	 * 
	 * @return Map
	 */
	Map<Departement, Integer> countByDepartement();

	/** Retourne si oui ou non une ville existe pour le code département donné
	 * @param nom nom de la ville
	 * @param code code du département
	 * @return boolean
	 */
	boolean existsByNomAndDepartementCode(String nom, String code);
}