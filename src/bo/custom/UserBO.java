package bo.custom;

import bo.SuperBO;
import dto.SignUpDTO;
import dto.SqDTO;
import dto.UserInfoDTO;

import java.util.ArrayList;

public interface UserBO extends SuperBO {
    public boolean save(UserInfoDTO dto) throws Exception;

    public boolean update(UserInfoDTO dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public ArrayList<UserInfoDTO> getAll() throws Exception;

    public String getUserLastId() throws Exception;

    public String getPasswordFromUserName(String userName) throws Exception;

    public String getPasswordFromUserId(String userId) throws Exception;

    public String getDesignation(String userName) throws Exception;

    public String getUserId(String userName) throws Exception;

    public void setUserId(String userName) throws Exception;

    public String getUserId() throws Exception;

    public String getUserFullName(String userId) throws Exception;

    public UserInfoDTO search(String id) throws Exception;

    public UserInfoDTO searchByNIC(String nic) throws Exception;

    public boolean updateBasicInfo(UserInfoDTO dto) throws Exception;

    public boolean updateUserBasic(UserInfoDTO dto) throws Exception;

    public boolean updateUserQA(UserInfoDTO dto) throws Exception;

    public boolean updateUserPassword(UserInfoDTO dto) throws Exception;

    public String getQuestionFromNumber(int no) throws Exception;

    public int getQuestionNumberFromQuestion(String question) throws Exception;

    public ArrayList<SqDTO> getAllSQ() throws Exception;

    public ArrayList<UserInfoDTO> getUsersDetails(String nic) throws Exception;

    public ArrayList<String> getAllQuestions() throws Exception;

    public boolean isUserNameAvailable(String userName) throws Exception;

    public boolean isPasswordAvailable(String password) throws Exception;

    public boolean updatePasswordAndSQ(SignUpDTO dto) throws Exception;
}
