package ar.com.juanek;

import java.util.ArrayList;
import java.util.List;

public class CarService implements ICarService
{
    /** {@inheritDoc} */
    @Override
    public List<Car> getCars()
    {
        List<Car> cars = new ArrayList<>();

        Car buick = new Car();
        buick.setYear("2018");
        buick.setMake("Buick");
        buick.setModel("Envision");
        cars.add(buick);

        Car ford = new Car();
        ford.setYear("2017");
        ford.setMake("Ford");
        ford.setModel("Escort");
        cars.add(ford);

        return cars;
    }

}
