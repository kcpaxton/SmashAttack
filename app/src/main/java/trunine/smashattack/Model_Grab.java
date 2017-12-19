package trunine.smashattack;


public class Model_Grab extends Model_Move{

    public String HitboxActiveRange;
    String FirstActionableFrame;


    public String getHitboxActiveRange() {
        return HitboxActiveRange;
    }

    public void setHitboxActiveRange(String hitboxActiveRange) {
        HitboxActiveRange = hitboxActiveRange;
    }

    public String getFirstActionableFrame() {
        return FirstActionableFrame;
    }

    public void setFirstActionableFrame(String firstActionableFrame) {
        FirstActionableFrame = firstActionableFrame;
    }
}