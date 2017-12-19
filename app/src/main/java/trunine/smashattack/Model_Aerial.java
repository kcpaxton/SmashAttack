package trunine.smashattack;

public class Model_Aerial extends Model_Move{

    public String HitboxActiveRange;
    public String FirstActionableFrame;
    public String BaseDamage;
    public String ShieldDamage;
    public String Angle;
    public String BaseKnockBack;
    public String WeightBaseKnockBack;
    public String KnockBackGrowth;
    public String LandingLag;
    public String AutoCancelFrames;

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

    public String getBaseDamage() {
        return BaseDamage;
    }

    public void setBaseDamage(String baseDamage) {
        BaseDamage = baseDamage;
    }

    public String getShieldDamage() {
        return ShieldDamage;
    }

    public void setShieldDamage(String shieldDamage) {
        ShieldDamage = shieldDamage;
    }

    public String getAngle() {
        return Angle;
    }

    public void setAngle(String angle) {
        Angle = angle;
    }

    public String getBaseKnockBack() {
        return BaseKnockBack;
    }

    public void setBaseKnockBack(String baseKnockBack) {
        BaseKnockBack = baseKnockBack;
    }

    public String getWeightBaseKnockBack() {
        return WeightBaseKnockBack;
    }

    public void setWeightBaseKnockBack(String weightBaseKnockBack) {
        WeightBaseKnockBack = weightBaseKnockBack;
    }

    public String getKnockBackGrowth() {
        return KnockBackGrowth;
    }

    public void setKnockBackGrowth(String knockBackGrowth) {
        KnockBackGrowth = knockBackGrowth;
    }

    public String getLandingLag() {
        return LandingLag;
    }

    public void setLandingLag(String landingLag) {
        LandingLag = landingLag;
    }

    public String getAutoCancelFrames() {
        return AutoCancelFrames;
    }

    public void setAutoCancelFrames(String autoCancelFrames) {
        AutoCancelFrames = autoCancelFrames;
    }
}
