public class UltratafiltrationRateSignal {
    private int value;

    public static final UltratafiltrationRateSignal INCREASE = new UltratafiltrationRateSignal(0);
    public static final UltratafiltrationRateSignal DECREASE = new UltratafiltrationRateSignal(1);
    public static final UltratafiltrationRateSignal NOTHING = new UltratafiltrationRateSignal(2);

    private UltratafiltrationRateSignal(int x) {
        value = x;
    }

    @Override
    public boolean equals(Object objectIn) {
        if (!(objectIn instanceof UltratafiltrationRateSignal)) return false; 
        UltratafiltrationRateSignal s = (UltratafiltrationRateSignal) objectIn;
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
