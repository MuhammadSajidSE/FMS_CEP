<<<<<<< HEAD
=======
// This is class to control dialysate flow rate signal 

>>>>>>> origin/huzaifaali
public class DialysateFlowRateSignal {
    private int value;

    public static final DialysateFlowRateSignal INCREASE = new DialysateFlowRateSignal(0);
    public static final DialysateFlowRateSignal DECREASE = new DialysateFlowRateSignal(1);
    public static final DialysateFlowRateSignal NOTHING = new DialysateFlowRateSignal(2);

    private DialysateFlowRateSignal(int x) {
        value = x;
    }

    @Override
    public boolean equals(Object objectIn) {
        if (!(objectIn instanceof DialysateFlowRateSignal)) return false; 
        DialysateFlowRateSignal s = (DialysateFlowRateSignal) objectIn;
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
