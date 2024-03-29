package org.diginamic.demojwt.beans;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/** Entité métier représentant un département
 * @author RichardBONNAMY
 *
 */
@Entity
public class Departement {

	/** Identifiant : clé primaire */
	@Id
	private int id;
	
	/** code */
	private String code;
	
	/** nom */
	private String nom;
	
	/** Villes du département */
	@OneToMany(mappedBy="departement")
	private List<Ville> villes;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/** Setter
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the villes
	 */
	public List<Ville> getVilles() {
		return villes;
	}

	/** Setter
	 * @param villes the villes to set
	 */
	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
	
}
