package de.tum.in.ase.eist.view;

import java.util.ArrayList;
import java.util.List;

public class EquippedWeapons {
    private List<AbstractWeapon> availableWeapons;
    private AbstractWeapon equippedWeapon;
    private WeaponPolicy policy;

    public EquippedWeapons() {
        availableWeapons = new ArrayList<AbstractWeapon>();
        policy = new WeaponPolicy("", this);
    }

    public void selectWeapon(String policy) {
        for (AbstractWeapon w : availableWeapons) {
            if (w.getRank()>equippedWeapon.getRank()) {
                equippedWeapon = w;
            };
        }
    }

    public AbstractWeapon shoot() {
        // TODO: refactor shooting so that it can be implemented per weapon
        return equippedWeapon;
    }


}
