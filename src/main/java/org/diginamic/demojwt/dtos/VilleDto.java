package org.diginamic.demojwt.dtos;

import org.diginamic.demojwt.beans.Ville;

/** Objet de transfert de données concernant une ville entre le client et le back.
 *
 * @author RichardBONNAMY
 *
 */
public class VilleDto {

	/** identifiant */
	private int id;
	
	/** nom */
	private String nom;
	
	/** population */
	private int population;
	
	/** code département */
	private String codeDept;
	
	/** Constructeur à partir d'une ville
	 * @param ville ville
	 */
	public VilleDto(Ville ville) {
		this.id = ville.getId();
		this.nom = ville.getNom();
		this.population = ville.getPopulation();
		this.codeDept = ville.getDepartement().getCode();
	}

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
	 * @return the codeDept
	 */
	public String getCodeDept() {
		return codeDept;
	}

	/** Setter
	 * @param codeDept the codeDept to set
	 */
	public void setCodeDept(String codeDept) {
		this.codeDept = codeDept;
	}

	
}
