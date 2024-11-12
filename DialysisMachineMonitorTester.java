import java.util.InputMismatchException;
import java.util.Scanner;

public class DialysisMachineMonitorTester {
    public DialysisMachineMonitorTester() {
    }
//sajid
    public static void main(String[] args) {
      
        DialysisMachineMonitoringSystem controller = new DialysisMachineMonitoringSystem();
        Scanner scanner = new Scanner(System.in);
        char choice;
        do {
            System.out.println("+--------------------------------------------+");
            System.out.println("|\tDialysis Machine Monitor Tester      |");
            System.out.println("+--------------------------------------------+");
            System.out.println("| 1. Set Parameters                          |");
            System.out.println("| 2. Activate System                         |");
            System.out.println("| 3. Request Parameter Change                |");
            System.out.println("| 4. Show Parameters                         |");
            System.out.println("| 5. Deactivate System                       |");
            System.out.println("| 6. Exit                                    |");
            System.out.print("| Enter your choice (1-6):                   |\n");
            System.out.println("+--------------------------------------------+");
            choice = scanner.next().charAt(0);
            scanner.nextLine();
            switch (choice) {
                case '1':
                    setParameters(controller, scanner);
                    break;
                case '2':
                    controller.activateSystem();
                    break;
                case '3':
                    requestParameterChange(controller, scanner);
                    break;
                case '4':
                    showParameters(controller);
                    break;
                case '5':
                    controller.deactivateSystem();
                    break;
                case '6':
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        } while (choice != '6');
        scanner.close();
    }

    private static void setParameters(DialysisMachineMonitoringSystem controller, Scanner scanner) {
        if (controller.getSystemStatus() == SystemStatus.ACTIVE) {
            System.out.println("Error: The system is in Activate mode. We can not intailze the paramters");
            return; 
        }
        char paramChoice;
        do {
            System.out.println("+--------------------------------------------+");
            System.out.println("|\tSet Parameters Menu                  |");
            System.out.println("+--------------------------------------------+");
            System.out.println("| 1. Set Blood Flow Rate                     |");
            System.out.println("| 2. Set Ultrafiltration Rate                |");
            System.out.println("| 3. Set Dialysate Flow Rate                 |");
            System.out.println("| 4. Return to Main Menu                     |");
            System.out.print("| Enter your choice (1-4):                   |\n");
            System.out.println("+--------------------------------------------+");
            paramChoice = scanner.next().charAt(0);
            scanner.nextLine(); 
            switch (paramChoice) {
                case '1':
                    setBloodFlowRate(controller, scanner);
                    break;
                case '2':
                    setUltrafiltrationRate(controller, scanner);
                    break;
                case '3':
                    setDialysateFlowRate(controller, scanner);
                    break;
                case '4':
                    break; 
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        } while (paramChoice != '4');
    }

    private static void setBloodFlowRate(DialysisMachineMonitoringSystem controller, Scanner scanner) {
        double bloodFlowRate = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter Blood Flow Rate (300-500): ");
                bloodFlowRate = scanner.nextDouble();
                controller.setInitialBloodFlowRate(bloodFlowRate);
                System.out.println("Blood Flow Rate set to: " + bloodFlowRate);
                validInput = true; 
            } catch (VDMException e) {
                System.out.println("Error setting Blood Flow Rate: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
                setParameters(controller, scanner);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
                scanner.next(); 
            }
        }
    }

    private static void setUltrafiltrationRate(DialysisMachineMonitoringSystem controller, Scanner scanner) {
        double ultrafiltrationRate = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter Ultrafiltration Rate (200-800): ");
                ultrafiltrationRate = scanner.nextDouble();
                controller.setInitialUltrafiltrationRate(ultrafiltrationRate);
                System.out.println("Ultrafiltration Rate set to: " + ultrafiltrationRate);
                validInput = true; 
    
            } catch (VDMException e) {
                System.out.println("Error setting Ultrafiltration Rate: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Ultrafiltration Rate input: " + e.getMessage());
                setParameters(controller, scanner);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
                scanner.next(); 
            }
        }
    }
    

    private static void setDialysateFlowRate(DialysisMachineMonitoringSystem controller, Scanner scanner) {
        double dialysateFlowRate = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter Dialysate Flow Rate (500-800): ");
                dialysateFlowRate = scanner.nextDouble();
                controller.setInitialDialysateFlowRate(dialysateFlowRate);
                System.out.println("Dialysate Flow Rate set to: " + dialysateFlowRate);
                validInput = true; 
    
            } catch (VDMException e) {
                System.out.println("Error setting Dialysate Flow Rate: " + e.getMessage());
                scanner.next(); 
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Dialysate Flow Rate input: " + e.getMessage());
                setParameters(controller, scanner);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
                scanner.next(); 
            }
        }
    }
    

    private static void requestParameterChange(DialysisMachineMonitoringSystem controller, Scanner scanner) {
        if (controller.getSystemStatus() == SystemStatus.DEACTIVE) {
            System.out.println("Error: The system is in Deactivate mode. We can not Request to change the paramters");
            return;  // Exit the method if the system is deactivated
        }
        char changeChoice;
        do {
            System.out.println("+--------------------------------------------+");
            System.out.println("|\tRequest Parameter Change Menu        |");
            System.out.println("+--------------------------------------------+");
            System.out.println("| 1. Change Blood Flow Rate                  |");
            System.out.println("| 2. Change Ultrafiltration Rate             |");
            System.out.println("| 3. Change Dialysate Flow Rate              |");
            System.out.println("| 4. Return to Main Menu                     |");
            System.out.print("| Enter your choice (1-4):                   |\n");
            System.out.println("+--------------------------------------------+");
            changeChoice = scanner.next().charAt(0);
            scanner.nextLine();
            switch (changeChoice) {
                case '1':
                    changeBloodFlowRate(controller, scanner);
                    break;
                case '2':
                    changeUltrafiltrationRate(controller, scanner);
                    break;
                case '3':
                    changeDialysateFlowRate(controller, scanner);
                    break;
                case '4':
                    break; 
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        } while (changeChoice != '4');
    }
    private static void changeBloodFlowRate(DialysisMachineMonitoringSystem controller, Scanner scanner) {
        try {
            System.out.print("Enter requested Blood Flow Rate: ");
            double requestedRate = scanner.nextDouble();
            BloodFlowRateSignal signal = controller.requestBloodFlowRateChange(requestedRate);
            while (signal != BloodFlowRateSignal.NOTHING) {
                if (signal == BloodFlowRateSignal.INCREASE) {
                    controller.incrementBloodFlowRate(); 
                } else if (signal == BloodFlowRateSignal.DECREASE) {
                    controller.decrementBloodFlowRate(); 
                }
                signal = controller.requestBloodFlowRateChange(requestedRate); // Re-evaluate the signal
            }
            
            System.out.println("Blood Flow Rate successfully changed to: " + controller.getActualBloodFlowRate());
    
        } catch (VDMException e) {
            System.out.println("Error requesting Blood Flow Rate change: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a numeric value.");
            scanner.next(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    

    private static void changeUltrafiltrationRate(DialysisMachineMonitoringSystem controller, Scanner scanner) {
        try {
            System.out.print("Enter requested Ultrafiltration Rate: ");
            double requestedRate = scanner.nextDouble();
            UltratafiltrationRateSignal signal = controller.requestUltrafiltrationRateChange(requestedRate);
            while (signal != UltratafiltrationRateSignal.NOTHING) {
                if (signal == UltratafiltrationRateSignal.INCREASE) {
                    controller.incrementUltrafiltrationRate(); 
                } else if (signal == UltratafiltrationRateSignal.DECREASE) {
                    controller.decrementUltrafiltrationRate(); 
                }
                signal = controller.requestUltrafiltrationRateChange(requestedRate); // Re-evaluate the signal
            }
            System.out.println("Ultrafiltration Rate successfully changed to: " + controller.getActualUltrafiltrationRate());
        } catch (VDMException e) {
            System.out.println("Error requesting Ultrafiltration Flow Rate change: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a numeric value.");
            scanner.next(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    

    private static void changeDialysateFlowRate(DialysisMachineMonitoringSystem controller, Scanner scanner) {
        try {
            System.out.print("Enter requested Dialysate Flow Rate: ");
            double requestedRate = scanner.nextDouble();
            DialysateFlowRateSignal signal = controller.requestDialysateFlowRateChange(requestedRate);
            while (signal != DialysateFlowRateSignal.NOTHING) {
                if (signal == DialysateFlowRateSignal.INCREASE) {
                    controller.incrementDialysateFlowRate(); 
                } else if (signal == DialysateFlowRateSignal.DECREASE) {
                    controller.decrementDialysateFlowRate(); 
                }
                signal = controller.requestDialysateFlowRateChange(requestedRate); // Re-evaluate the signal
            }
            System.out.println("Dialysate Flow Rate successfully changed to: " + controller.getActualDialysateFlowRate());
        }catch (VDMException e) {
            System.out.println("Error requesting Dialysate Flow Rate change: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a numeric value.");
            scanner.next(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
    private static void showParameters(DialysisMachineMonitoringSystem controller) {
        System.out.println("Current Parameters:");
        System.out.println("Actual Blood Flow Rate: " + controller.getActualBloodFlowRate());
        System.out.println("Actual Ultrafiltration Rate: " + controller.getActualUltrafiltrationRate());
        System.out.println("Actual Dialysate Flow Rate: " + controller.getActualDialysateFlowRate());
    }
}
