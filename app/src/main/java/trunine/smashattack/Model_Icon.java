package trunine.smashattack;

public class Model_Icon {

    public int FighterId;
    public String Name;
    public String IconUrl;

    public void setName(String name) {
        Name = name;
    }
    public void setFighterId(int fighterId) {
        FighterId = fighterId;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }
    public int getFighterId(){
        return this.FighterId;
    }
    public String getName(){
        return this.Name;
    }
    public String getUrl(){ return this.IconUrl; }

}