package ra.crud.service;

import org.springframework.web.multipart.MultipartFile;
import ra.crud.model.Students;

import java.io.IOException;
import java.util.List;

public interface StudentsService {
    List<Students> findAll();
    Students findById(int id);
    boolean save(Students students,  MultipartFile avatarFile) throws IOException;
    boolean update(Students students,  MultipartFile avatarFile) throws IOException;
    boolean delete(int studentId);
}
