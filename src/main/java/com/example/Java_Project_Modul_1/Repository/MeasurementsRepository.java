package com.example.Java_Project_Modul_1.Repository;

import com.example.Java_Project_Modul_1.Model.Measurement_Current;

import java.util.List;

/*Так как это интерфейс, аннотаций ставить здесь не надо. Создаем методы, которые будем реализовывать
* в классе [MeasurementsRepository_Reliz]. Необходим метод, который будет вносить данные в базу данны [addDatabase]. Необходим метод, который
* будет брать определенные данные из базы данных и сверять значения токов в каждой из фаз с уставкой.*/
public interface MeasurementsRepository {
    void addDatabase(Measurement_Current measurement_current); //Метод, который вносит данные с файла в базу данных
    List<Measurement_Current> List_Measurement(int startIndex, int endIndex); //Метод, который берет некоторые данные для сравнения токов с уставкой
}
