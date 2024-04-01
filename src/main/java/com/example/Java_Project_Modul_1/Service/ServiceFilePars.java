package com.example.Java_Project_Modul_1.Service;

import org.springframework.web.multipart.MultipartFile;

/*Так как это интерфейс, аннотаций ставить здесь не надо. Создаем методы, которые будут реализованы
в классе [ServiceFilePars_Reliz]. Необходимо создать метод, который будет производить парсинг принимаемого
в [RestConrollerIzmer] и передаваемого на уровень Service (данный уровень) файла. Необходимо создать метод,
который будет считывать файл и сравнивать хранящиеся в нем значения с заданной уставкой, для
выявления короткого замыкания. Необходимо создать метол, который будет принимать значение уставки с уровня
Controller.*/
public interface ServiceFilePars {
    void parseFile(MultipartFile file); //Метод для парсинга файла
    String identify_KZ_time(int startIndex, int endIndex); //Метод для выявления короткого замыкания в фазе
    void identify_KZ_ystavka(double setPoint); //Метод для установление устовки по тока, для дальнейшего использования в методе [identify_KZ_time]
}