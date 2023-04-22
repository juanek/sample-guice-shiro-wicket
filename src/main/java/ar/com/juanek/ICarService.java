package ar.com.juanek;

import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(CarService.class)
public interface ICarService
{
    /**
     * Gets a list of cars.
     *
     * @return a list of cars
     */
    public List<Car> getCars();
}
