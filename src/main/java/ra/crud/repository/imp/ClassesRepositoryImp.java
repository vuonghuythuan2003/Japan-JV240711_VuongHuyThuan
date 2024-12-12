package ra.crud.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.crud.model.Classes;
import ra.crud.repository.ClassesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository

public class ClassesRepositoryImp implements ClassesRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Classes> findAll() {
        return entityManager.createQuery("from Classes", Classes.class).getResultList();
    }

    @Override
    public Classes findById(int classId) {
        return entityManager.find(Classes.class, classId);
    }
    @Transactional
    @Override
    public boolean save(Classes classes) {
        try{
            entityManager.persist(classes);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Lỗi thêm lớp");
        }
        return false;
    }
    @Transactional
    @Override
    public boolean update(Classes classes) {
        try{
            entityManager.merge(classes);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Lỗi chỉnh sửa lớp");
        }
        return false;
    }
    @Transactional
    @Override
    public boolean delete(int classId) {
        try{
            entityManager.remove(entityManager.find(Classes.class, classId));
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println("Lỗi xóa lớp");
        }
        return false;
    }

    @Override
    public List<Classes> findPaginated(int page, int size) {
        Query query = entityManager.createQuery("FROM Classes", Classes.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
