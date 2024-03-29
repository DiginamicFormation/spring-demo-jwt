package org.diginamic.demojwt.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/** Entité métier représentant une ville
 * @author RichardBONNAMY
 *
 */
@Entity
public class Ville {

	/** Identifiant : clé primaire */
	@Id
	private int id;
	
	/** nom */
	private String nom;
	
	/** population */
	private int population;
	
	/** Département associé */
	@ManyToOne
	@JoinColumn(name="id_dept")
	private Departement departement;

	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/** Setter
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/** Getter
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/** Setter
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
}
