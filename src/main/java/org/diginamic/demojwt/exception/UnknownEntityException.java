package org.diginamic.demojwt.exception;

/**
 * Exception jetée dans le cas d'une information non trouvée (ex: ville non trouvée)
 * 
 * @author RichardBONNAMY
 *
 */
public class UnknownEntityException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 7570512369410916632L;

	/** Constructeur
	 * @param msg message d'erreur
	 */
	public UnknownEntityException(String msg) {
		super(msg);
	}
}
