package com.ghali.automation;

public interface DriverInterface {
	/**
	 *
	 * @return Object contains a driver based on platform chosen
	 */
	Object getDriver();

	/**
	 * Closes the driver
	 */

	void closeDriver();

}
