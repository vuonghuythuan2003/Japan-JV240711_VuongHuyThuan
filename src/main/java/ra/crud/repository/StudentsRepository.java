package ra.crud.repository;

import ra.crud.model.Students;

import java.util.List;

public interface StudentsRepository {
    List<Students> findAll();
    Students findById(int id);
    boolean save(Students students);
    boolean update(Students students);
    boolean delete(int studentId);
    boolean existsByStudentName(String studentName);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByStudentEmail(String studentEmail);

}
