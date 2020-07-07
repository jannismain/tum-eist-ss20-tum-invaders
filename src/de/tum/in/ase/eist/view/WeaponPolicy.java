package de.tum.in.ase.eist.view;

public class WeaponPolicy {

    private EquippedWeapons weapons;

    private String policy = "highestRank";

    public WeaponPolicy(String policy, EquippedWeapons w) {
        if (!policy.equals("")) {
            this.policy = policy;
        }
        weapons = w;
    }


}
