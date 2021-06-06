package statistics.stock;

import java.util.Scanner;

public class driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path of csv file: ");
        String filePath = sc.nextLine();
        System.out.println("Enter the path of file containing monthwise closing prices:");
        String testFilePath = sc.nextLine();
        System.out.println("Enter the path of the Output file: ");
        String OutputFilepath = sc.nextLine();
        mathematicalOperations operations = new mathematicalOperations();
        operations.Return_price(filePath, testFilePath, OutputFilepath);
    }
}



