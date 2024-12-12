package ra.crud.service;

import ra.crud.model.Classes;

import java.util.List;

public interface ClassesService {
    List<Classes> findAll();
    Classes findById(int classId);
    boolean save(Classes classes);
    boolean update(Classes classes);
    boolean delete(int classId);
    List<Classes> findPaginated(int page, int size);

}
