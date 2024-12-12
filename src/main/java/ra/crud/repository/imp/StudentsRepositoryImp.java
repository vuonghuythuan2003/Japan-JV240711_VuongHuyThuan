package ra.crud.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.crud.model.Students;
import ra.crud.repository.StudentsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class StudentsRepositoryImp implements StudentsRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Students> findAll() {
        return entityManager.createQuery("from Students", Students.class).getResultList();
    }

    @Override
    public Students findById(int id) {
        return entityManager.find(Students.class, id);
    }

    @Transactional
    @Override
    public boolean save(Students students) {
        try{
            entityManager.persist(students);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Thêm học sinh thất bại");
        }
        return false;
    }

    @Transactional
    @Override
    public boolean update(Students students) {
        try{
            entityManager.merge(students);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Chỉnh sửa học sinh thất bại");
        }
        return false;
    }

    @Transactional
    @Override
    public boolean delete(int studentId) {
        try{
            entityManager.remove(entityManager.find(Students.class, studentId));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Xóa học sinh thất bại");
        }
        return false;
    }

    @Override
    public boolean existsByStudentName(String studentName) {
        String query = "SELECT COUNT(s) FROM Students s WHERE s.studentName = :studentName";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("studentName", studentName)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        String query = "SELECT COUNT(s) FROM Students s WHERE s.phoneNumber = :phoneNumber";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByStudentEmail(String studentEmail) {
        String query = "SELECT COUNT(s) FROM Students s WHERE s.studentEmail = :studentEmail";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("studentEmail", studentEmail)
                .getSingleResult();
        return count > 0;
    }

}
