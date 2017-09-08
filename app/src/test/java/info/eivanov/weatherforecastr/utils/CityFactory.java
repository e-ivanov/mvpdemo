package info.eivanov.weatherforecastr.utils;

import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.model.Sys;

/**
 * Created by killer on 9/8/17.
 */

public class CityFactory {

    public static City createTestDummy(){
        City city = new City();
        city.setId(123456L);
        city.setDefault(false);
        city.setName("London");
        Sys sys =new Sys();
        sys.setId(145L);
        sys.setCountry("GB");
        sys.setMessage(45.0);
        sys.setSunrise(123456789L);
        sys.setSunset(987654321L);
        sys.setType(1L);
        city.setSys(sys);
        return city;
    }
}
