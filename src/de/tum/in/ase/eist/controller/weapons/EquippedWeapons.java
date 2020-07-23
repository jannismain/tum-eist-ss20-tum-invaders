package de.tum.in.ase.eist.controller.weapons;

import java.util.ArrayList;
import java.util.List;

public class EquippedWeapons {
    private List<AbstractWeapon> weapons;

    private AbstractWeapon equippedWeapon;
    private WeaponPolicy policy;

    public EquippedWeapons() {
        weapons = new ArrayList<AbstractWeapon>();
        equippedWeapon = null;
        policy = new WeaponPolicy();
    }

    public void selectWeapon() {
        this.setEquippedWeapon(this.policy.select(this));
    }

    public AbstractWeapon shoot() {
        // TODO: refactor shooting so that it can be implemented per weapon
        return equippedWeapon;
    }

    public void addWeapon(AbstractWeapon w) {
        if (!this.weapons.contains(w)) {
            this.weapons.add(w);
        }
    }

    public AbstractWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(AbstractWeapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public List<AbstractWeapon> getWeapons() {
        return weapons;
    }

    public void setPolicy(WeaponPolicy policy) {
        this.policy = policy;
    }

}
