package ra.crud.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.crud.model.Students;
import ra.crud.repository.StudentsRepository;
import ra.crud.service.StudentsService;
import ra.crud.service.UploadFileService;

import java.io.IOException;
import java.util.List;
@Service
public class StudentsServiceImp implements StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private final UploadFileService uploadFileService;

    public StudentsServiceImp(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @Override
    public List<Students> findAll() {
        return studentsRepository.findAll();
    }

    @Override
    public Students findById(int id) {
        return studentsRepository.findById(id);
    }

    @Override
    public boolean save(Students students,  MultipartFile avatarFile) throws IOException {
        if (studentsRepository.existsByStudentName(students.getStudentName())) {
            throw new IllegalArgumentException("Tên sinh viên đã tồn tại.");
        }
        if (studentsRepository.existsByPhoneNumber(students.getPhoneNumber())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại.");
        }
        if (studentsRepository.existsByStudentEmail(students.getStudentEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại.");
        }
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String imageUrl = uploadFileService.uploadFile(avatarFile);
            students.setImageUrl(imageUrl);
        }
        return studentsRepository.save(students);
    }

    @Override
    public boolean update(Students students,  MultipartFile avatarFile) throws IOException {

        if (avatarFile != null && !avatarFile.isEmpty()) {
            String imageUrl = uploadFileService.uploadFile(avatarFile);
            students.setImageUrl(imageUrl);
        }
        return studentsRepository.update(students);
    }

    @Override
    public boolean delete(int studentId) {
        return studentsRepository.delete(studentId);
    }
}
