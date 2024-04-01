package com.example.Java_Project_Modul_1.Repository;

import com.example.Java_Project_Modul_1.Model.Measurement_Current;

import java.util.List;

public interface MeasurementsRepository {
    void addDatabase(Measurement_Current measurement_current);
    List<Measurement_Current> List_Measurement(int startIndex, int endIndex);
}
