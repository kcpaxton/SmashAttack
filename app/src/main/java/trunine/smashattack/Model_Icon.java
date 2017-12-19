package trunine.smashattack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Model_Icon {

    public int FighterId;
    public String Name;
    public String IconUrl;

    public void setFighterId(int fighterId) {
        FighterId = fighterId;
    }
    public void setName(String name) {
        Name = name;
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
    public String getIconUrl(){ return this.IconUrl; }

}
