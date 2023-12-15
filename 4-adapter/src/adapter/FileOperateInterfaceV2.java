package adapter;

import java.util.List;

public interface FileOperateInterfaceV2 extends ManageStaffInterface {
    List<StaffModel> readAllStaff();

    void listAllStaff(List<StaffModel> list);

    void writeByName(List<StaffModel> list);

    void writeByRoom(List<StaffModel> list);
}
