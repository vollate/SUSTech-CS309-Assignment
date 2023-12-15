package adapter;

import java.util.Comparator;
import java.util.List;

public class Adapter implements FileOperateInterfaceV2 {
    FileOperateInterfaceV1 adaptee;
    ManageStaffInterface staffManager;

    public Adapter(FileOperateInterfaceV1 adaptee, ManageStaffInterface staffManager) {
        this.adaptee = adaptee;
        this.staffManager = staffManager;
    }

    @Override
    public List<StaffModel> readAllStaff() {
        return adaptee.readStaffFile();
    }

    @Override
    public void listAllStaff(List<StaffModel> list) {
        adaptee.printStaffFile(list);
    }

    @Override
    public void writeByName(List<StaffModel> list) {
        list.sort(Comparator.comparing(StaffModel::getName));
        adaptee.writeStaffFile(list);
    }

    @Override
    public void writeByRoom(List<StaffModel> list) {
        list.sort(Comparator.comparing(StaffModel::getRoom));
        adaptee.writeStaffFile(list);
    }

    @Override
    public void addingStaff(List<StaffModel> list) {
        staffManager.addingStaff(list);
    }

    @Override
    public void removeStaff(List<StaffModel> list) {
        staffManager.removeStaff(list);
    }
}
