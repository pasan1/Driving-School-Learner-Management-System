package dao.custom;

import dao.CrudDAO;
import entity.Instructor;

import java.util.ArrayList;

public interface InstructorDAO extends CrudDAO<Instructor, String> {
    public Instructor searchByNIC(String nic) throws Exception;

    public String getInstructorLastId() throws Exception;

    public ArrayList<Instructor> getSearchInstructorDetails(String id) throws Exception;

    public String getInstructorNICFromId(String id) throws Exception;

    public String getInstructorNameFromId(String id) throws Exception;

    public int getCount() throws Exception;
}
