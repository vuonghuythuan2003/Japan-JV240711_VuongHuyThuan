package ra.crud.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.crud.model.Classes;
import ra.crud.repository.ClassesRepository;
import ra.crud.service.ClassesService;

import java.util.List;
@Service
public class ClassesServiceImp implements ClassesService {
    @Autowired
    private ClassesRepository classesRepository;
    @Override
    public List<Classes> findAll() {
        return classesRepository.findAll();
    }

    @Override
    public Classes findById(int classId) {
        return classesRepository.findById(classId);
    }

    @Override
    public boolean save(Classes classes) {
        return classesRepository.save(classes);
    }

    @Override
    public boolean update(Classes classes) {
        return classesRepository.update(classes);
    }

    @Override
    public boolean delete(int classId) {
        return classesRepository.delete(classId);
    }

    @Override
    public List<Classes> findPaginated(int page, int size) {
        int offset = (page - 1) * size;
        return classesRepository.findPaginated(offset, size);
    }
}
