package de.tum.in.ase.eist.controller.weapons;

public class WeaponPolicy {

    public WeaponPolicy() {
    }

    public AbstractWeapon select(EquippedWeapons ew) {
        AbstractWeapon selectedWeapon = ew.getEquippedWeapon();
        for (AbstractWeapon w : ew.getWeapons()) {
            if (w.getRank() > selectedWeapon.getRank()) {
                selectedWeapon = w;
            }
        }
        return selectedWeapon;
    }

}
