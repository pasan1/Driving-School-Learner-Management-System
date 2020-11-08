package bo.custom;

import bo.SuperBO;
import dto.InstructorDTO;

import java.util.ArrayList;

public interface InstructorBO extends SuperBO {
    public String getInstructorLastId() throws Exception;

    public ArrayList<InstructorDTO> getAll() throws Exception;

    public boolean save(InstructorDTO dto) throws Exception;

    public boolean update(InstructorDTO dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public InstructorDTO search(String id) throws Exception;

    public ArrayList<InstructorDTO> getSearchInstructorDetails(String id) throws Exception;

    public InstructorDTO searchByNIC(String nic) throws Exception;
}
