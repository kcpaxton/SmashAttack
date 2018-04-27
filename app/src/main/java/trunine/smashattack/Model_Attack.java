package trunine.smashattack;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class Model_Attack extends Model_Move{

    public String HitboxActiveRange;
    public String FirstActionableFrame;
    public String BaseDamage;
    public String ShieldDamage;
    public String Angle ;
    public String BaseKnockBack ;
    public String WeightBaseKnockBack;
    public String KnockBackGrowth;

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
}
