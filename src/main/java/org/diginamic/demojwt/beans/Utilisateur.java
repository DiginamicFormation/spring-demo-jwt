package org.diginamic.demojwt.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Entité métier représentant un utilisateur
 * @author RichardBONNAMY
 *
 */
@Entity
@Table(name="utilisateur")
public class Utilisateur {

	/** Identifiant : clé primaire */
	@Id
	private Integer id;
	
	/** nom */
	private String nom;
	
	/** prénom */
	private String prenom;
	
	/** email */
	private String email;
	
	/** password */
	private String password;
	
	/** Profil de sécurité (USER ou ADMIN) */
	private String role;

	/** Getter
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/** Setter
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/** Getter
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/** Setter
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/** Getter
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/** Setter
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Getter
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/** Setter
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
