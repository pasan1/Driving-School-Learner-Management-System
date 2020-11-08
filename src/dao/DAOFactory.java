package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, CUSTOMERPROGRAM, INSTRUCTOR, PAYMENT, SCHEDULE, SQ, USER, VEHICLE, QUERY
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case CUSTOMERPROGRAM:
                return new CustomerProgramDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SCHEDULE:
                return new ScheduleDAOImpl();
            case SQ:
                return new SqDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
