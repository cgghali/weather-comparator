package com.ghali.automation.comparator;

import com.ghali.automation.factory.UtilityFactory;

public class WeatherComparatorWithVariance {
	public WeatherComparatorWithVariance() {

	}

	public boolean WeatherComparator(Weather weatherObjWeb, Weather weatherObjApi) {
		Float.parseFloat(UtilityFactory.getJavaUtils().getConfigMap().get("tempretureVariance"));
		float humidVariance = Float.parseFloat(UtilityFactory.getJavaUtils().getConfigMap().get("humidityVariance"));


		if ((Float.compare(weatherObjWeb.getHumidity(), weatherObjApi.getHumidity()) == 0)
				|| ((Float.compare(weatherObjWeb.getHumidity(), weatherObjApi.getHumidity()) <= humidVariance))
						&& (Float.compare(weatherObjWeb.getTempreture(), weatherObjApi.getTempreture()) == 0)
				|| (Float.compare(weatherObjWeb.getTempreture(), weatherObjApi.getTempreture()) <= humidVariance)) {

			return true;
		} else {
			return false;
		}
	}
}


