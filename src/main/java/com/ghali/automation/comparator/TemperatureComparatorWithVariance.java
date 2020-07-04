package com.ghali.automation.comparator;

import com.ghali.automation.factory.UtilityFactory;

public class TemperatureComparatorWithVariance {
	public TemperatureComparatorWithVariance() {

	}

	public boolean TemperatureComparator(float temprWeb, float tempApi_kelvin) {
		boolean retValue = false;
		float difference;
		String varianceTemp = UtilityFactory.getJavaUtils().getConfigMap().get("tempretureVariance");
		float variance = Float.parseFloat(varianceTemp);
		// Converting Kelvin to Celsius
		float tempApi = tempApi_kelvin - 273.15F;
		if (Float.compare(temprWeb, tempApi) == 0)
		{
			retValue = true;
		}
		else {

			difference = temprWeb - tempApi;
			if (Float.compare(Math.abs(difference), variance) <= variance) {
				retValue = true;
			}
		}
		return retValue;
	}

}
