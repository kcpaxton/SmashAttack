package trunine.smashattack;

import java.util.List;

public class Model_Fighter {

    public int Id;
    public String Name;
    public String Title;
    public String IconUrl;
    public String PortraitPictureUrl;
    public Model_Attributes Attributes;
    public List<Model_Attack> Attacks;
    public List<Model_Grab> Grabs;
    public List<Model_Throw> Throws;
    public List<Model_Roll> Rolls;
    public List<Model_Aerial> Aerials ;
    public List<Model_Special> Specials;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public String getPortraitPictureUrl() {
        return PortraitPictureUrl;
    }

    public void setPortraitPictureUrl(String portraitPictureUrl) {
        PortraitPictureUrl = portraitPictureUrl;
    }

    public Model_Attributes getAttributes() {
        return Attributes;
    }

    public void setAttributes(Model_Attributes attributes) {
        Attributes = attributes;
    }

    public List<Model_Attack> getAttacks() {
        return Attacks;
    }

    public void setAttacks(List<Model_Attack> attacks) {
        Attacks = attacks;
    }

    public List<Model_Grab> getGrabs() {
        return Grabs;
    }

    public void setGrabs(List<Model_Grab> grabs) {
        Grabs = grabs;
    }

    public List<Model_Throw> getThrows() {
        return Throws;
    }

    public void setThrows(List<Model_Throw> aThrows) {
        Throws = aThrows;
    }

    public List<Model_Roll> getRolls() {
        return Rolls;
    }

    public void setRolls(List<Model_Roll> rolls) {
        Rolls = rolls;
    }

    public List<Model_Aerial> getAerials() {
        return Aerials;
    }

    public void setAerials(List<Model_Aerial> aerials) {
        Aerials = aerials;
    }

    public List<Model_Special> getSpecials() {
        return Specials;
    }

    public void setSpecials(List<Model_Special> specials) {
        Specials = specials;
    }
}
