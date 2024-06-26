package com.example.Java_Project_Modul_1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data //Аннотация, которая автоматически создает getter и setter для всех переменных
@Entity //Аннотация, которая указывает, что данный класс должен храниться в базе данных
@Table(name = "Measurement_Current") //Аннотация, которая задает имя таблицы в базе данных
@NoArgsConstructor //Аннотация, которая используется для создания конструкторов без аргумента
@AllArgsConstructor //Аннотация, которая используется для создания параметризованных конструкторов с одним параметром для каждого поля

public class Measurement_Current { //Создание класса параметров, которые будут храниться в базе данных
    @Id //Сущность аннотации @Entity, которая говорит, что данное поле будет являться первичным ключом (поле [Id])
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id; //Переменная целочисленного типа с модификатором доступа private, которая будет нужна для считывания id-ников с файла
    @Column //Аннотация для контроля маппинга колонок в таблице базы данных
    private double Time; //Переменная целочисленного типа с модификатором доступа private, которая будет нужна для считывания времени с файла
    @Column //Аннотация для контроля маппинга колонок в таблице базы данных
    private double ia; //Переменная целочисленного типа с модификатором доступа private, которая будет нужна для считывания тока ia с файла
    @Column //Аннотация для контроля маппинга колонок в таблице базы данных
    private double ib; //Переменная целочисленного типа с модификатором доступа private, которая будет нужна для считывания тока ib с файла
    @Column //Аннотация для контроля маппинга колонок в таблице базы данных
    private double ic; //Переменная целочисленного типа с модификатором доступа private, которая будет нужна для считывания тока ic с файла
}