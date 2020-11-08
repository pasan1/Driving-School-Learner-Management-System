package bo.custom.impl;

import bo.custom.UserBO;
import dao.DAOFactory;
import dao.custom.SqDAO;
import dao.custom.UserDAO;
import dto.SignUpDTO;
import dto.SqDTO;
import dto.UserInfoDTO;
import entity.SQ;
import entity.User;

import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    SqDAO sqDAO = (SqDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SQ);

    private static String userId;

    @Override
    public boolean save(UserInfoDTO dto) throws Exception {
        return userDAO.save(new User(
                dto.getUserId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getGender(),
                dto.getDob(),
                dto.getAddress(),
                dto.getMobileNumber(),
                dto.getDrivingLicense(),
                dto.getDesignation(),
                dto.getUserName(),
                dto.getPassword(),
                getQuestionNumberFromQuestion(dto.getQ1()),
                getQuestionNumberFromQuestion(dto.getQ2()),
                getQuestionNumberFromQuestion(dto.getQ3()),
                dto.getA1(),
                dto.getA2(),
                dto.getA3()
        ));
    }

    @Override
    public boolean update(UserInfoDTO dto) throws Exception {
        return userDAO.update(new User(
                dto.getUserId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getGender(),
                dto.getDob(),
                dto.getAddress(),
                dto.getMobileNumber(),
                dto.getDrivingLicense(),
                dto.getDesignation(),
                dto.getUserName(),
                dto.getPassword(),
                getQuestionNumberFromQuestion(dto.getQ1()),
                getQuestionNumberFromQuestion(dto.getQ2()),
                getQuestionNumberFromQuestion(dto.getQ3()),
                dto.getA1(),
                dto.getA2(),
                dto.getA3()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public ArrayList<UserInfoDTO> getAll() throws Exception {
        ArrayList<User> all = userDAO.getAll();
        ArrayList<UserInfoDTO> allUsers = new ArrayList<>();
        for (User u : all) {
            UserInfoDTO dto = new UserInfoDTO(
                    u.getUserId(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getNic(),
                    u.getGender(),
                    u.getDob(),
                    u.getAddress(),
                    u.getMobileNumber(),
                    u.getDrivingLicense(),
                    u.getDesignation(),
                    u.getUserName(),
                    u.getPassword(),
                    getQuestionFromNumber(u.getQ1()),
                    getQuestionFromNumber(u.getQ2()),
                    getQuestionFromNumber(u.getQ3()),
                    u.getA1(),
                    u.getA2(),
                    u.getA3()
            );
            allUsers.add(dto);
        }
        return allUsers;
    }

    @Override
    public String getUserLastId() throws Exception {
        return userDAO.getUserLastId();
    }

    @Override
    public String getPasswordFromUserName(String userName) throws Exception {
        return userDAO.getPasswordFromUserName(userName);
    }

    @Override
    public String getPasswordFromUserId(String userId) throws Exception {
        return userDAO.getPasswordFromUserId(userId);
    }

    @Override
    public String getDesignation(String userName) throws Exception {
        return userDAO.getDesignation(userName);
    }

    @Override
    public String getUserId(String userName) throws Exception {
        return userDAO.getUserId(userName);
    }

    @Override
    public void setUserId(String userName) throws Exception {
        this.userId=getUserId(userName);
    }

    @Override
    public String getUserId() throws Exception {
        return userId;
    }

    @Override
    public String getUserFullName(String userId) throws Exception {
        return userDAO.getUserFull(userId);
    }

    @Override
    public UserInfoDTO search(String id) throws Exception {
        User u = userDAO.search(id);
        return new UserInfoDTO(
                u.getUserId(),
                u.getFirstName(),
                u.getLastName(),
                u.getNic(),
                u.getGender(),
                u.getDob(),
                u.getAddress(),
                u.getMobileNumber(),
                u.getDrivingLicense(),
                u.getDesignation(),
                u.getUserName(),
                u.getPassword(),
                getQuestionFromNumber(u.getQ1()),
                getQuestionFromNumber(u.getQ2()),
                getQuestionFromNumber(u.getQ3()),
                u.getA1(),
                u.getA2(),
                u.getA3()
        );
    }

    @Override
    public UserInfoDTO searchByNIC(String nic) throws Exception {
        User u = userDAO.searchByNIC(nic);
        return new UserInfoDTO(
                u.getUserId(),
                u.getFirstName(),
                u.getLastName(),
                u.getNic(),
                u.getGender(),
                u.getDob(),
                u.getAddress(),
                u.getMobileNumber(),
                u.getDrivingLicense(),
                u.getDesignation(),
                u.getUserName(),
                u.getPassword(),
                getQuestionFromNumber(u.getQ1()),
                getQuestionFromNumber(u.getQ2()),
                getQuestionFromNumber(u.getQ3()),
                u.getA1(),
                u.getA2(),
                u.getA3()
        );
    }

    @Override
    public boolean updateBasicInfo(UserInfoDTO dto) throws Exception {
        return userDAO.updateUserBasicInfo(new User(
                dto.getUserId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getGender(),
                dto.getDob(),
                dto.getAddress(),
                dto.getMobileNumber(),
                dto.getDrivingLicense(),
                dto.getDesignation()
        ));
    }

    @Override
    public boolean updateUserBasic(UserInfoDTO dto) throws Exception {
        return userDAO.updateUserBasicInfo(new User(
                dto.getUserId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getGender(),
                dto.getDob(),
                dto.getAddress(),
                dto.getMobileNumber(),
                dto.getDrivingLicense(),
                dto.getDesignation(),
                dto.getUserName()
        ));
    }

    @Override
    public boolean updateUserQA(UserInfoDTO dto) throws Exception {
        return userDAO.updateUserQA(new User(
                dto.getUserId(),
                getQuestionNumberFromQuestion(dto.getQ1()),
                getQuestionNumberFromQuestion(dto.getQ2()),
                getQuestionNumberFromQuestion(dto.getQ3()),
                dto.getA1(),
                dto.getA2(),
                dto.getA3()
        ));
    }

    @Override
    public boolean updateUserPassword(UserInfoDTO dto) throws Exception {
        return userDAO.updateUserPassword(new User(
                dto.getUserId(),
                dto.getPassword()
        ));
    }

    @Override
    public String getQuestionFromNumber(int no) throws Exception {
        return sqDAO.getQuestionFromNumber(no);
    }

    @Override
    public int getQuestionNumberFromQuestion(String question) throws Exception {
        return sqDAO.getQuestionNumberFromQuestion(question);
    }

    @Override
    public ArrayList<SqDTO> getAllSQ() throws Exception {
        ArrayList<SQ> all = sqDAO.getAll();
        ArrayList<SqDTO> list = new ArrayList<>();
        for (SQ sq : all) {
            list.add(new SqDTO(sq.getSqNo(), sq.getQuestion()));
        }
        return list;
    }

    @Override
    public ArrayList<UserInfoDTO> getUsersDetails(String nic) throws Exception {
        ArrayList<User> all = userDAO.getUsersDetails(nic);
        ArrayList<UserInfoDTO> allUsers = new ArrayList<>();
        for (User u : all) {
            UserInfoDTO dto = new UserInfoDTO(
                    u.getUserId(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getNic(),
                    u.getGender(),
                    u.getDob(),
                    u.getAddress(),
                    u.getMobileNumber(),
                    u.getDrivingLicense(),
                    u.getDesignation(),
                    u.getUserName(),
                    u.getPassword(),
                    getQuestionFromNumber(u.getQ1()),
                    getQuestionFromNumber(u.getQ2()),
                    getQuestionFromNumber(u.getQ3()),
                    u.getA1(),
                    u.getA2(),
                    u.getA3()
            );
            allUsers.add(dto);
        }
        return allUsers;
    }

    @Override
    public ArrayList<String> getAllQuestions() throws Exception {
        ArrayList<SQ> all = sqDAO.getAll();
        ArrayList<String> list = new ArrayList<>();
        for (SQ s : all) {
            list.add(s.getQuestion());
        }
        return list;
    }

    @Override
    public boolean isUserNameAvailable(String userName) throws Exception {
        return userDAO.isUserNameAvailable(userName);
    }

    @Override
    public boolean isPasswordAvailable(String password) throws Exception {
        return userDAO.isPasswordAvailable(password);
    }

    @Override
    public boolean updatePasswordAndSQ(SignUpDTO dto) throws Exception {
        return userDAO.updatePasswordAndSQ(new User(
                dto.getUserId(),
                dto.getPassword(),
                getQuestionNumberFromQuestion(dto.getQ1S()),
                getQuestionNumberFromQuestion(dto.getQ2S()),
                getQuestionNumberFromQuestion(dto.getQ3S()),
                dto.getA1(),
                dto.getA2(),
                dto.getA3()
        ));
    }
}
