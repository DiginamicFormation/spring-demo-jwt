package org.diginamic.demojwt.exception;

/**
 * Exception jetée dans le cas d'une règle métier non respectée ou d'une
 * information non trouvée.
 * 
 * @author RichardBONNAMY
 *
 */
public class FunctionalException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 7570512369410916632L;

	/** Constructeur
	 * @param msg message d'erreur
	 */
	public FunctionalException(String msg) {
		super(msg);
	}
}
