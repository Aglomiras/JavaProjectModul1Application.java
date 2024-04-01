package com.example.Java_Project_Modul_1.Repository;

import com.example.Java_Project_Modul_1.Model.Measurement_Current;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository //Аннотация, говорящая, что данный класс принадлежит уровню Repository
@Transactional //Аннотация, говорящая, что в данном классе производится транзакция
public class MeasurementsRepository_Reliz implements MeasurementsRepository {
    @PersistenceContext //Аннотация, которая внедряет прокси, который выполняет открытие и закрытие EntityManager автоматически
    private EntityManager entityManager; //Создание сущности
    @Override //Аннотация, которая проверяет, переопределен ли метод. Вызывает ошибку компиляции, если метод не найден в родительском классе
    public void addDatabase(Measurement_Current measurement_current) { //Метод, который добавляет данные в базу данных
        if (measurement_current.getId() == 0) { //Если в базе данных нет данных, то выгружает их
            entityManager.persist(measurement_current); //Использование метода, для выгрузки даннвх а базу данных
        } else { //Если в базе данных, есть данные, то перезаписывает их
            entityManager.merge(measurement_current); //Использование метода, для перезаписи данных в базе данных
        }
    }
    @Override //Аннотация, которая проверяет, переопределен ли метод. Вызывает ошибку компиляции, если метод не найден в родительском классе
    public List<Measurement_Current> List_Measurement(int startIndex, int endIndex) { //Метод, принимающий начало и конец проверяемого интервала
        List<Measurement_Current> measurement_current_List = entityManager
                .createQuery("Select m from Measurement_Current m where m.Id >= :startIndex and m.Id <= :endIndex", Measurement_Current.class)
                .setParameter("startIndex", startIndex)
                .setParameter("endIndex", endIndex)
                .getResultList(); //Перевод значений в лист
        return measurement_current_List; //Возвращает значения токов, измеренных за определенный интервал времени
    }
}
