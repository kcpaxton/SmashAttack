package trunine.smashattack;

public class Model_Roll extends Model_Move{

    public String Intangibility;
    public String FirstActionableFrame;

    public String getIntangibility() {
        return Intangibility;
    }

    public void setIntangibility(String intangibility) {
        Intangibility = intangibility;
    }

    public String getFirstActionableFrame() {
        return FirstActionableFrame;
    }

    public void setFirstActionableFrame(String firstActionableFrame) {
        FirstActionableFrame = firstActionableFrame;
    }
}

