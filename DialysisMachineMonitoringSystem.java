public class DialysisMachineMonitoringSystem implements InvariantCheck{
   public static final  int MaxBloodFlowRate = 500;
   public static final int MinBloodFlowRate = 300;
   public static final int MaxUltrafiltrationRate = 800;
   public static final int MinUltrafiltrationRate = 200;
   public static final int MaxDialysateFlowRate = 800;
   public static final int MinDialysateFlowRate = 500;

   public static final int NIL = -999;

   private double  actualBloodFlowRate;
   private double requestedBloodFlowRate;
   private  double actualUltrafiltrationRate;
   private  double requestedUltrafiltrationRate;
   private  double actualDialysateFlowRate;
   private double requestedDialysateFlowRate;

   private SystemStatus systemStatus;


   @Override
   public boolean inv() {
       return (actualBloodFlowRate == NIL || inRangeBloodFlowRate(actualBloodFlowRate)) &&
              (requestedBloodFlowRate == NIL || inRangeBloodFlowRate(requestedBloodFlowRate)) &&
              (actualUltrafiltrationRate == NIL || inRangeUltrafiltrationRate(actualUltrafiltrationRate)) &&
              (requestedUltrafiltrationRate == NIL || inRangeUltrafiltrationRate(requestedUltrafiltrationRate)) &&
              (actualDialysateFlowRate == NIL || inRangeDialysateFlowRate(actualDialysateFlowRate)) &&
              (requestedDialysateFlowRate == NIL || inRangeDialysateFlowRate(requestedDialysateFlowRate));
   }
   

   public DialysisMachineMonitoringSystem(){
       this.actualBloodFlowRate = NIL;
       this.requestedBloodFlowRate = NIL;
       this.actualUltrafiltrationRate = NIL;
       this.requestedUltrafiltrationRate = NIL;
       this.actualDialysateFlowRate = NIL;
       this.requestedDialysateFlowRate = NIL;
       this.systemStatus = SystemStatus.DEACTIVE; 
   }

   public String getActualBloodFlowRate() {
      return actualBloodFlowRate == NIL ? "NIL" : String.valueOf(actualBloodFlowRate);
  }
  
  public String getActualUltrafiltrationRate() {
      return actualUltrafiltrationRate == NIL ? "NIL" : String.valueOf(actualUltrafiltrationRate);
  }
  
  public String getActualDialysateFlowRate() {
      return actualDialysateFlowRate == NIL ? "NIL" : String.valueOf(actualDialysateFlowRate);
  }
  
   private boolean inRangeBloodFlowRate (double  val ) {
       return val >= MinBloodFlowRate && val <= MaxBloodFlowRate;
   }
   
   private boolean inRangeUltrafiltrationRate (double  val ) {
       return val >= MinUltrafiltrationRate && val <= MaxUltrafiltrationRate;
   }
   
   private boolean inRangeDialysateFlowRate (double  val ) {
       return val >= MinDialysateFlowRate && val <= MaxDialysateFlowRate;
   }
   
   public void setInitialBloodFlowRate(double bloodRate) throws VDMException {
    if (actualBloodFlowRate != NIL) {
        throw new VDMException("Cannot set BloodFlowRate: actual BloodFlowRate is already set.");
    }
      VDM.preTest(actualBloodFlowRate == NIL && inRangeBloodFlowRate(bloodRate));
     
       if (!inRangeBloodFlowRate(bloodRate)) {
           throw new VDMException("BloodFlowRate out of range");
       }
       actualBloodFlowRate = bloodRate;
       VDM.invTest(this);
   }

   public void setInitialUltrafiltrationRate(double filtrationRate) throws VDMException {
    if (actualUltrafiltrationRate != NIL) {
        throw new VDMException(" Cannot set UltrafiltrationRate: actual UltrafiltrationRate is already set.");
    }
      VDM.preTest(actualUltrafiltrationRate == NIL && inRangeUltrafiltrationRate(filtrationRate)); 
     
       
       if (!inRangeUltrafiltrationRate(filtrationRate)) {
           throw new VDMException(" UltrafiltrationRate out of range");
       }
       actualUltrafiltrationRate = filtrationRate;
       VDM.invTest(this);
   }

   public void setInitialDialysateFlowRate(double DialysateRate ) throws VDMException {
    if (actualDialysateFlowRate != NIL) {
        throw new VDMException("Cannot set DialysateRate: actual DialysateRate is already set.");
    }
      VDM.preTest(actualDialysateFlowRate == NIL && inRangeDialysateFlowRate(DialysateRate ));
       
      
       if (!inRangeDialysateFlowRate(DialysateRate)) {
           throw new VDMException("DialysateRate  out of range");
       }
       actualDialysateFlowRate = DialysateRate ;
       VDM.invTest(this);
   }

   public void incrementBloodFlowRate() throws VDMException {
      double newRate = this.actualBloodFlowRate + 0.1;
      VDM.preTest(inRangeBloodFlowRate(this.actualBloodFlowRate - 0.1));
      if (this.inRangeBloodFlowRate(newRate)) {
        this.actualBloodFlowRate = Math.round(newRate * 10.0) / 10.0;
      } else {
         throw new VDMException("BloodFlowRate exceeds minimum limit");
      }
      VDM.invTest(this);
    }
 
    public void decrementBloodFlowRate() throws VDMException {
      double newRate = this.actualBloodFlowRate - 0.1;
       VDM.preTest(inRangeBloodFlowRate(this.actualBloodFlowRate - 0.1));
       if (this.inRangeBloodFlowRate(newRate)) {
         this.actualBloodFlowRate = Math.round(newRate * 10.0) / 10.0;
       } else {
          throw new VDMException("BloodFlowRate exceeds minimum limit");
       }
       VDM.invTest(this);
    }

    public void incrementUltrafiltrationRate() throws VDMException {
      double newRate = this.actualUltrafiltrationRate + 0.1;
      VDM.preTest(inRangeUltrafiltrationRate(newRate));
       if (inRangeUltrafiltrationRate(newRate)) {
          this.actualUltrafiltrationRate = Math.round(newRate * 10.0) / 10.0;
       } else {
          throw new VDMException("UltrafiltrationRate exceeds minimum limit");
       }
       VDM.invTest(this);
    }
 
    public void decrementUltrafiltrationRate() throws VDMException {
      double newRate = this.actualDialysateFlowRate - 0.1;
      VDM.preTest(inRangeUltrafiltrationRate(newRate));
       if (inRangeUltrafiltrationRate(newRate)) {
          this.actualUltrafiltrationRate = Math.round(newRate * 10.0) / 10.0;
       } else {
          throw new VDMException("UltrafiltrationRate exceeds minimum limit");
       }
       VDM.invTest(this);
    }

public void incrementDialysateFlowRate() throws VDMException {
   double newRate = this.actualDialysateFlowRate + 0.1;
   VDM.preTest(inRangeDialysateFlowRate(newRate));
   
   if (inRangeDialysateFlowRate(newRate)) {
       this.actualDialysateFlowRate = Math.round(newRate * 10.0) / 10.0;
   } else {
       throw new VDMException("DialysateFlowRate exceeds maximum limit");
   }
   
   VDM.invTest(this);
}
  
  public void decrementDialysateFlowRate() throws VDMException {
      double newRate = this.actualDialysateFlowRate - 0.1;
      VDM.preTest(inRangeDialysateFlowRate(newRate));
      if (inRangeDialysateFlowRate(newRate)) {
          this.actualDialysateFlowRate = Math.round(newRate * 10.0) / 10.0;
      } else {
          throw new VDMException("DialysateFlowRate exceeds minimum limit");
      }
      VDM.invTest(this);
  }
  

    public BloodFlowRateSignal requestBloodFlowRateChange(double bloodRate) throws VDMException {
        if (systemStatus == SystemStatus.DEACTIVE) {
            throw new VDMException("Cannot change BloodFlowRate: System is in DEACTIVE mode.");
        }
      VDM.preTest(inRangeBloodFlowRate(bloodRate) && actualBloodFlowRate!=NIL && systemStatus == SystemStatus.ACTIVE);
      if (this.actualBloodFlowRate == NIL) {
          throw new VDMException("BloodFlowRate not set yet");
      } else if (bloodRate == NIL) {
          throw new VDMException("Requested BloodFlowRate not set yet");
      }
      else if (bloodRate > this.actualBloodFlowRate) {
         return BloodFlowRateSignal.INCREASE;
     }  else if (bloodRate < this.actualBloodFlowRate) {
          return BloodFlowRateSignal.DECREASE;
      } 
      else if (bloodRate == this.actualBloodFlowRate) {
         return BloodFlowRateSignal.NOTHING;
      }
      else {
          return BloodFlowRateSignal.NOTHING;
      }
  }
  
  public UltratafiltrationRateSignal requestUltrafiltrationRateChange(double filtrationRate) throws VDMException {
    if (systemStatus == SystemStatus.DEACTIVE) {
        throw new VDMException("Cannot change UltraFiltrationRate: System is in DEACTIVE mode.");
    }
   VDM.preTest(inRangeUltrafiltrationRate(filtrationRate) && actualUltrafiltrationRate!=NIL && systemStatus == SystemStatus.ACTIVE);
      if (this.actualUltrafiltrationRate == NIL) {
          throw new VDMException("UltrafiltrationRate not set yet");
      } else if (filtrationRate == NIL) {
          throw new VDMException("Requested UltrafiltrationRate not set yet");
      }
      else if (filtrationRate > this.actualUltrafiltrationRate) {
         return UltratafiltrationRateSignal.INCREASE;
     } 
       else if (filtrationRate < this.actualUltrafiltrationRate) {
          return UltratafiltrationRateSignal.DECREASE;
      } 
      else if (filtrationRate == this.actualUltrafiltrationRate) {
         return UltratafiltrationRateSignal.NOTHING;
      }
      else {
          return UltratafiltrationRateSignal.NOTHING;
      }
  }
  public DialysateFlowRateSignal requestDialysateFlowRateChange(double dialysateRate) throws VDMException {
    if (systemStatus == SystemStatus.DEACTIVE) {
        throw new VDMException("Cannot change Dialysate: System is in DEACTIVE mode.");
    }
   VDM.preTest(inRangeDialysateFlowRate(dialysateRate) && actualDialysateFlowRate!=NIL && systemStatus == SystemStatus.ACTIVE);
   if (this.actualDialysateFlowRate == NIL) {
       throw new VDMException("DialysateFlowRate not set yet");
   } else if (dialysateRate == NIL) {
       throw new VDMException("Requested DialysateFlowRate not set yet");
   } else if (dialysateRate < this.actualDialysateFlowRate) {
       return DialysateFlowRateSignal.DECREASE;
   } else if (dialysateRate > this.actualDialysateFlowRate) {
       return DialysateFlowRateSignal.INCREASE;
   } 
   else if (dialysateRate == this.actualDialysateFlowRate) {
      return DialysateFlowRateSignal.NOTHING;
  }
   else {
       return DialysateFlowRateSignal.NOTHING;
   }
}

  
  public void activateSystem() {
   if (systemStatus == SystemStatus.ACTIVE) {
       System.out.println("System is already in ACTIVE mode.");
       return; 
   }
   if (actualBloodFlowRate == NIL) {
       System.out.println("BloodFlowRate not set yet. Please set the BloodFlowRate first.");
   }  if (actualUltrafiltrationRate == NIL) {
       System.out.println("UltrafiltrationRate not set yet. Please set the UltrafiltrationRate first.");
   }  if (actualDialysateFlowRate == NIL) {
       System.out.println("DialysateFlowRate not set yet. Please set the DialysateFlowRate first.");  
   } else {
       systemStatus = SystemStatus.ACTIVE;
       System.out.println("System has been started.");
   }
}


 public void deactivateSystem() {
     if (systemStatus == SystemStatus.DEACTIVE) {
         System.out.println("System is already in DEACTIVE mode.");
         return; 
     }
     actualBloodFlowRate = NIL;
     requestedBloodFlowRate = NIL;
     actualUltrafiltrationRate = NIL;
     requestedUltrafiltrationRate = NIL;
     actualDialysateFlowRate = NIL;
     requestedDialysateFlowRate = NIL;
     systemStatus = SystemStatus.DEACTIVE;
     System.out.println("System has been deactivated.");
 }
 public SystemStatus getSystemStatus() {
    return systemStatus;
}
}