package trunine.smashattack;

import java.util.ArrayList;
import java.util.List;

public class DisplayFighterGroup {
    private String name;
    private List<Model_Attack> attacks = new ArrayList<Model_Attack>();
    private List<Model_Aerial> aerials = new ArrayList<Model_Aerial>();
    private List<Model_Special> specials = new ArrayList<Model_Special>();
    private List<Model_Grab> grabs = new ArrayList<Model_Grab>();
    private List<Model_Throw> throwsList = new ArrayList<Model_Throw>();
    private List<Model_Roll> rolls = new ArrayList<Model_Roll>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model_Attack> getAttacks() {
        return attacks;
    }

    public List<Model_Aerial> getAerials() {
        return aerials;
    }

    public List<Model_Special> getSpecials() {
        return specials;
    }

    public List<Model_Grab> getGrabs() {
        return grabs;
    }

    public List<Model_Throw> getThrowsList() {
        return throwsList;
    }

    public List<Model_Roll> getRolls() {
        return rolls;
    }

    public void setAttackProductList(List<Model_Attack> productList) {
        this.attacks = productList;
    }
    public void setAerialsProductList(List<Model_Aerial> productList) {
        this.aerials = productList;
    }
    public void setSpecialsProductList(List<Model_Special> productList) {
        this.specials = productList;
    }
    public void setGrabsProductList(List<Model_Grab> productList) {
        this.grabs = productList;
    }
    public void setThrowsProductList(List<Model_Throw> productList) {
        this.throwsList = productList;
    }
    public void setRollsProductList(List<Model_Roll> productList) {
        this.rolls = productList;
    }

}

