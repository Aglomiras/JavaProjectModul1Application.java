package com.example.Java_Project_Modul_1.Repository;

import com.example.Java_Project_Modul_1.Model.Measurement_Current;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class MeasurementsRepository_Reliz implements MeasurementsRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addDatabase(Measurement_Current measurement_current) {
        if (measurement_current.getId() == 0) {
            entityManager.persist(measurement_current);
        } else {
            entityManager.merge(measurement_current);
        }
    }
    @Override
    public List<Measurement_Current> List_Measurement(int startIndex, int endIndex) {
        List<Measurement_Current> measurement_current_List = entityManager
                .createQuery("We take measurements from a given range (startIndex <= x <= endIndex)", Measurement_Current.class)
                .setParameter("startIndex", startIndex)
                .setParameter("endIndex", endIndex)
                .getResultList();
        return measurement_current_List;
    }
}
