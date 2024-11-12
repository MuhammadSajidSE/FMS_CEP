public class SystemStatus {
    public static final SystemStatus ACTIVE = new SystemStatus(0);
    public static final SystemStatus DEACTIVE = new SystemStatus(1);

    private int status;

    private SystemStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public boolean isActive() {
        return status == 0; 
    }
    
    public boolean isDeactive() {
        return status == 1; 
    }
}
