package de.tum.in.ase.eist.collision;

import de.tum.in.ase.eist.car.Car;

public class TopCollision extends Collision {

    public TopCollision(Car car1, Car car2) {
        super(car1, car2);
    }

    /**
     * Evaluates winner of the collision
     *
     * @return winner Car
     */
    public Car evaluate() {
        Car winnerCar = null;
        if (this.car1.getPosition().getY() < this.car2.getPosition().getY()) {
            winnerCar = this.car2;
        } else {
            winnerCar = this.car1;
        }
        return winnerCar;
    }

}