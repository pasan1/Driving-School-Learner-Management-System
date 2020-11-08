package bo.custom.impl;

import bo.custom.InstructorBO;
import dao.DAOFactory;
import dao.custom.InstructorDAO;
import dto.InstructorDTO;
import entity.Instructor;

import java.util.ArrayList;

public class InstructorBOIml implements InstructorBO {
    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);

    @Override
    public String getInstructorLastId() throws Exception {
        return instructorDAO.getInstructorLastId();
    }

    @Override
    public ArrayList<InstructorDTO> getAll() throws Exception {
        ArrayList<Instructor> all = instructorDAO.getAll();
        ArrayList<InstructorDTO> allInstructors = new ArrayList<>();
        for (Instructor i : all) {
            InstructorDTO dto = new InstructorDTO(
                    i.getInstructorId(),
                    i.getFirstName(),
                    i.getLastName(),
                    i.getNic(),
                    i.getGender(),
                    i.getDob(),
                    i.getAddress(),
                    i.getMobileNumber(),
                    i.getDrivingLicense(),
                    i.getEpfNo()
            );
            allInstructors.add(dto);
        }
        return allInstructors;
    }

    @Override
    public boolean save(InstructorDTO dto) throws Exception {
        return instructorDAO.save(new Instructor(dto.getInstructorId(), dto.getFirstName(), dto.getLastName(), dto.getNic(), dto.getGender(), dto.getDob(), dto.getAddress(), dto.getMobileNumber(), dto.getDrivingLicense(), dto.getEpfNo()));
    }

    @Override
    public boolean update(InstructorDTO dto) throws Exception {
        return instructorDAO.update(new Instructor(dto.getInstructorId(), dto.getFirstName(), dto.getLastName(), dto.getNic(), dto.getGender(), dto.getDob(), dto.getAddress(), dto.getMobileNumber(), dto.getDrivingLicense(), dto.getEpfNo()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return instructorDAO.delete(id);
    }

    @Override
    public InstructorDTO search(String id) throws Exception {
        Instructor i = instructorDAO.search(id);
        return new InstructorDTO(
                i.getInstructorId(),
                i.getFirstName(),
                i.getLastName(),
                i.getNic(),
                i.getGender(),
                i.getDob(),
                i.getAddress(),
                i.getMobileNumber(),
                i.getDrivingLicense(),
                i.getEpfNo()
        );
    }

    @Override
    public ArrayList<InstructorDTO> getSearchInstructorDetails(String id) throws Exception {
        ArrayList<Instructor> all = instructorDAO.getSearchInstructorDetails(id);
        ArrayList<InstructorDTO> allInstructors = new ArrayList<>();
        for (Instructor i : all) {
            InstructorDTO dto = new InstructorDTO(
                    i.getInstructorId(),
                    i.getFirstName(),
                    i.getLastName(),
                    i.getNic(),
                    i.getGender(),
                    i.getDob(),
                    i.getAddress(),
                    i.getMobileNumber(),
                    i.getDrivingLicense(),
                    i.getEpfNo()
            );
            allInstructors.add(dto);
        }
        return allInstructors;
    }

    @Override
    public InstructorDTO searchByNIC(String nic) throws Exception {
        Instructor i = instructorDAO.searchByNIC(nic);
        return new InstructorDTO(
                i.getInstructorId(),
                i.getFirstName(),
                i.getLastName(),
                i.getNic(),
                i.getGender(),
                i.getDob(),
                i.getAddress(),
                i.getMobileNumber(),
                i.getDrivingLicense(),
                i.getEpfNo()
        );
    }
}
