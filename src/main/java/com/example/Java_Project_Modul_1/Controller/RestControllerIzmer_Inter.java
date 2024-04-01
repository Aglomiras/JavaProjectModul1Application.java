package com.example.Java_Project_Modul_1.Controller;
import org.springframework.web.multipart.MultipartFile;

/*Так как это интерфейс, аннотаций ставить здесь не надо. Создаем методы, которые будем реализовывать
* в классе [RestControllerIzmer]. Необходимо создать один метод, который будет принимать POST запрос,
* и два метода, которые будут принимать GET запрос.*/
public interface RestControllerIzmer_Inter {
    public void Post_method(MultipartFile file); //Интерфейс метода по парсингу принимаемого файла
    public String Get_method_time(int startIndex, int endIndex); //Интерфейс метода по определеннию КЗ на выбранном диапазоне
    public void Get_method_ystavka(double startIndex); //Интерфейс метода по заданию уставки тока
}
