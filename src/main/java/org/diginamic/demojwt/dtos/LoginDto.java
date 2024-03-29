package org.diginamic.demojwt.dtos;

/** Objet de transfert de données de login entre le client et le back.
 * @author RichardBONNAMY
 *
 */
public class LoginDto {

	/** username */
	private String username;
	
	/** password */
	private String password;

	/** Getter
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/** Setter
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
