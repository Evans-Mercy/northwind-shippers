import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShippersDao dao = new ShippersDao();

        System.out.println("Enter new shipper name: ");
        String name = scanner.nextLine();

        System.out.println("Enter phone: ");
        String phone = scanner.nextLine();

        int newId = dao.i
    }
}
