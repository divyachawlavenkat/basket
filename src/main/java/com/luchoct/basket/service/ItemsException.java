/**
 * 
 */
package com.luchoct.basket.service;

import java.util.List;

/**
 * An exception over some items.
 * @author Luis
 *
 */
public class ItemsException extends Exception {
	
	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 685440193403744455L;

	/**
	 * It creates the exception.
	 * @param ids The list of ids of the items that cause the exception.
	 * @param message The message.
	 */
	public ItemsException (List<String> ids, String message) {
		super(message + "Ids " + ids);
	}

}
