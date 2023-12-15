package adapter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        List<StaffModel> list = new ArrayList<>();
        FileOperateInterfaceV1 adaptee = new FileOperate();
        ManageStaffInterface staffManager = new ManageStaff();
        FileOperateInterfaceV2 adapter = new Adapter(adaptee, staffManager);
        Scanner input = new Scanner(System.in);
        System.out.println("Please select operation: 1.readFile 2.listFile 3.writeByName 4.writeByRoom 5.Add Staff 6.Remove Staff");
        int op = 0;
        do {
            try {
                op = input.nextInt();
                switch (op) {
                    case 1:list = adapter.readAllStaff();
                        break;
                    case 2:
                        adapter.listAllStaff(list);
                        break;
                    case 3:
                        adapter.writeByName(list);
                        break;
                    case 4:
                        adapter.writeByRoom(list);
                        break;
                    case 5:
                        staffManager.addingStaff(list);
                        break;
                    case 6:
                        staffManager.removeStaff(list);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Exception:" + e);
                input.nextLine();
            }
        } while (op != 0);
        input.close();
    }
}
