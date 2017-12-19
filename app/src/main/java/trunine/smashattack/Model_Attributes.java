package trunine.smashattack;


public class Model_Attributes extends Model_Move{

    public String Weight;
    public int WeightRank;
    public String RunSpeed;
    public int RunSpeedRank;
    public String WalkSpeed;
    public int WalkSpeedRank;
    public String AirSpeed;
    public int AirSpeedRank;
    public String FallSpeed;
    public int FallSpeedRank;
    public String FastFallSpeed;
    public int FastFallSpeedRank;
    public String AirAcceleration;
    public String Gravity;
    public String ShortHopAirTimeFrames;
    public String FullHopAirTimeFrames ;
    public String MaximumJumps;
    public boolean WallJump;
    public boolean WallCling;
    public boolean Crawl;
    public boolean Tether;
    public String JumpSquatFrames;
    public String SoftLandingLagFrames;
    public String HardLandingLagFrames;

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public int getWeightRank() {
        return WeightRank;
    }

    public void setWeightRank(int weightRank) {
        WeightRank = weightRank;
    }

    public String getRunSpeed() {
        return RunSpeed;
    }

    public void setRunSpeed(String runSpeed) {
        RunSpeed = runSpeed;
    }

    public int getRunSpeedRank() {
        return RunSpeedRank;
    }

    public void setRunSpeedRank(int runSpeedRank) {
        RunSpeedRank = runSpeedRank;
    }

    public String getWalkSpeed() {
        return WalkSpeed;
    }

    public void setWalkSpeed(String walkSpeed) {
        WalkSpeed = walkSpeed;
    }

    public int getWalkSpeedRank() {
        return WalkSpeedRank;
    }

    public void setWalkSpeedRank(int walkSpeedRank) {
        WalkSpeedRank = walkSpeedRank;
    }

    public String getAirSpeed() {
        return AirSpeed;
    }

    public void setAirSpeed(String airSpeed) {
        AirSpeed = airSpeed;
    }

    public int getAirSpeedRank() {
        return AirSpeedRank;
    }

    public void setAirSpeedRank(int airSpeedRank) {
        AirSpeedRank = airSpeedRank;
    }

    public String getFallSpeed() {
        return FallSpeed;
    }

    public void setFallSpeed(String fallSpeed) {
        FallSpeed = fallSpeed;
    }

    public int getFallSpeedRank() {
        return FallSpeedRank;
    }

    public void setFallSpeedRank(int fallSpeedRank) {
        FallSpeedRank = fallSpeedRank;
    }

    public String getFastFallSpeed() {
        return FastFallSpeed;
    }

    public void setFastFallSpeed(String fastFallSpeed) {
        FastFallSpeed = fastFallSpeed;
    }

    public int getFastFallSpeedRank() {
        return FastFallSpeedRank;
    }

    public void setFastFallSpeedRank(int fastFallSpeedRank) {
        FastFallSpeedRank = fastFallSpeedRank;
    }

    public String getAirAcceleration() {
        return AirAcceleration;
    }

    public void setAirAcceleration(String airAcceleration) {
        AirAcceleration = airAcceleration;
    }

    public String getGravity() {
        return Gravity;
    }

    public void setGravity(String gravity) {
        Gravity = gravity;
    }

    public String getShortHopAirTimeFrames() {
        return ShortHopAirTimeFrames;
    }

    public void setShortHopAirTimeFrames(String shortHopAirTimeFrames) {
        ShortHopAirTimeFrames = shortHopAirTimeFrames;
    }

    public String getFullHopAirTimeFrames() {
        return FullHopAirTimeFrames;
    }

    public void setFullHopAirTimeFrames(String fullHopAirTimeFrames) {
        FullHopAirTimeFrames = fullHopAirTimeFrames;
    }

    public String getMaximumJumps() {
        return MaximumJumps;
    }

    public void setMaximumJumps(String maximumJumps) {
        MaximumJumps = maximumJumps;
    }

    public boolean isWallJump() {
        return WallJump;
    }

    public void setWallJump(boolean wallJump) {
        WallJump = wallJump;
    }

    public String getWallJump(){
        if(isWallJump())
        {
            return "Yes";
        }
        else
        {
            return "No";
        }
    }

    public boolean isWallCling() {
        return WallCling;
    }

    public void setWallCling(boolean wallCling) {
        WallCling = wallCling;
    }

    public String getWallCling(){
        if(isWallCling())
        {
            return "Yes";
        }
        else
        {
            return "No";
        }
    }


    public boolean isCrawl() {
        return Crawl;
    }

    public void setCrawl(boolean crawl) {
        Crawl = crawl;
    }

    public String getCrawl(){
        if(isCrawl())
        {
            return "Yes";
        }
        else
        {
            return "No";
        }
    }


    public boolean isTether() {
        return Tether;
    }

    public void setTether(boolean tether) {
        Tether = tether;
    }

    public String getTether(){
        if(isTether())
        {
            return "Yes";
        }
        else
        {
            return "No";
        }
    }


    public String getJumpSquatFrames() {
        return JumpSquatFrames;
    }

    public void setJumpSquatFrames(String jumpSquatFrames) {
        JumpSquatFrames = jumpSquatFrames;
    }

    public String getSoftLandingLagFrames() {
        return SoftLandingLagFrames;
    }

    public void setSoftLandingLagFrames(String softLandingLagFrames) {
        SoftLandingLagFrames = softLandingLagFrames;
    }

    public String getHardLandingLagFrames() {
        return HardLandingLagFrames;
    }

    public void setHardLandingLagFrames(String hardLandingLagFrames) {
        HardLandingLagFrames = hardLandingLagFrames;
    }
}
