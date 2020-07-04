package com.ghali.automation.factory;

import com.ghali.automation.interfaces.DriverInterface;
import com.ghali.automation.pages.NdtvHomePage;
import com.ghali.automation.pages.NdtvWeatherPage;

/**
 * Creates PageFactory for web page.
 *
 * @param String
 *
 */
public class NdtvPageFactory {

	private DriverInterface driverInterface;

	public NdtvPageFactory(DriverInterface driverInterface) {
		this.driverInterface = driverInterface;
	}

	public NdtvHomePage getNdtvHomePage() {
		return new  NdtvHomePage(driverInterface);
	}

	public NdtvWeatherPage getNdtvWeatherPage() {
		return new NdtvWeatherPage(driverInterface);
	}

}
