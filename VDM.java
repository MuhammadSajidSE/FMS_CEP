class VDM {
    public static void preTest(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("Precondition violated");
        }
    }

    public static void invTest(InvariantCheck obj) {
        if (!obj.inv()) {
            throw new IllegalStateException("Invariant violated");
        }
    }
}
