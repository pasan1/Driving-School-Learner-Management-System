package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;

    }

    public enum BOTypes {
        USER, CUSTOMER, INSTRUCTOR, SCHEDULE, PAYMENT, VEHICLE, DEFAULT
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case USER:
                return new UserBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case INSTRUCTOR:
                return new InstructorBOIml();
            case SCHEDULE:
                return new ScheduleBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case DEFAULT:
                return new DefaultBOImpl();
            default:
                return null;

        }
    }
}
