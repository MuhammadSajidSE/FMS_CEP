public class BloodFlowRateSignal {
    private int value;

    public static final BloodFlowRateSignal INCREASE = new BloodFlowRateSignal(0);
    public static final BloodFlowRateSignal DECREASE = new BloodFlowRateSignal(1);
    public static final BloodFlowRateSignal NOTHING = new BloodFlowRateSignal(2);

    private BloodFlowRateSignal(int x) {
        value = x;
    }

    @Override
    public boolean equals(Object objectIn) {
        if (!(objectIn instanceof BloodFlowRateSignal)) return false; 
        BloodFlowRateSignal s = (BloodFlowRateSignal) objectIn;
        return value == s.value;
    }

    @Override
    public String toString() {
        switch (value) {
            case 0: return "INCREASE";
            case 1: return "DECREASE";
            default: return "NOTHING"; 
        }
    }  
}
