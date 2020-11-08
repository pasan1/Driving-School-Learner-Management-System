package dao.custom;

import dao.CrudDAO;
import entity.SQ;

public interface SqDAO extends CrudDAO<SQ,String> {
    public String getQuestionFromNumber(int no) throws Exception;

    public int getQuestionNumberFromQuestion(String question) throws Exception;
}
