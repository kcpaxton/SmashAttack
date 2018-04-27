package trunine.smashattack;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder(alphabetic = true)
public class Model_Move {

    public String Name;
    public List<String> AbilityFramePictureUrls;

    public String getName() {
        return Name;
    }

    public List<String> getAbilityFramePictureUrls() {
        return AbilityFramePictureUrls;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAbilityFramePictureUrls(List<String> abilityFramePictureUrls) {
        AbilityFramePictureUrls = abilityFramePictureUrls;
    }
}