import java.util.Scanner;
public class SimpleBanking {

    public static void main(String[] args) {
        Scanner myrequest = new Scanner(System.in);
        int initialBalance = 1000;
        System.out.println("Welcome to the simple Banking System! \n 1. Check balance \n 2. Deposit Money \n 3. Withdraw Money\n 4. Exit");
        System.out.print("Choose option: ");
        int options = myrequest.nextInt();
        //check user option and go to the rewquired process
        switch (options) {
            case 1://checking balance😂😂😂
                System.out.println("Your Account Balance is: "+initialBalance);
                break;
            case 2://kuweka mali humu tu😊😊
                System.out.print("Enter Amount:");
                int depositAmount = myrequest.nextInt();
                initialBalance += depositAmount;
                System.out.println("Your New Account Balance is: "+initialBalance);
                break;
            case 3://kutoa mpunga😁😁
                System.out.print("Enter Withdrawal Amount: ");
                int withdrawalAmount = myrequest.nextInt();
                if (withdrawalAmount>initialBalance) {
                    System.out.println("Your Account Balance is: "+initialBalance +" "+ "INSUFFICIENT FOR YOUR REQUEST");
                }
                else{
                    initialBalance-=withdrawalAmount;
                    System.out.println("Successfully withdrawn: "+withdrawalAmount);
                    System.out.println("Your current Account Balance is: "+initialBalance);
                }
                break;
            case 4://humu tu
                System.out.println("Thank you for using our service!!!");
                break;
            default://we zombie
                System.out.println("INVALID OPTION");
                break;
        }
        myrequest.close();
    }
}
