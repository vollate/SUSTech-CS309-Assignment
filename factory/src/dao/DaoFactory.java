package dao;

public interface DaoFactory {
    ComputerDao createComputerDao();
    StaffDao createStaffDao();
}
