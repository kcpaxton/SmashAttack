package trunine.smashattack;

public class Model_Throw extends Model_Move{

    public boolean WeightDependent ;
    public String BaseDamage ;
    public String ShieldDamage;
    public String Angle;
    public String BaseKnockBack;
    public String KnockBackGrowth;

    public boolean isWeightDependent() {
        return WeightDependent;
    }

    public void setWeightDependent(boolean weightDependent) {
        WeightDependent = weightDependent;
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

    public String getKnockBackGrowth() {
        return KnockBackGrowth;
    }

    public void setKnockBackGrowth(String knockBackGrowth) {
        KnockBackGrowth = knockBackGrowth;
    }
}
