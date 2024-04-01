package com.example.Java_Project_Modul_1.Controller;

import com.example.Java_Project_Modul_1.Service.ServiceFilePars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*Перед начало работы, необходимо настроить pom.xml файл, чтобы работать именно в Java-spring.
* Для этого необходимо зайти на сайт: [https://start.spring.io/] и сформировать проект с необходимым
* нам pom.xml файлом. На сайте нужно установить следующие настройки:
* Project: Maven
* Language: Java
* Spring Boot: version 3.0.5
* Project Metadata: назвать самому
* Packaging: Jar
* Java: version 17
* А также необходимо загрузить несколько дополнений: Spring Web; H2 Database; Spring Data JPA; Lombok.
* После основной настройки, необходимо загрузить сформированный архив, разархивировать его на ПК и запустить
* проект.
* */

/*Реализованные в данном классе методы принимают в себя определенные данные и передают их на уровень Service,
* где будет происходить дальнейшая работа.
* Метод [Post_method] принимает файл и передает его на уровень Service, где происходит парсинг файла.
* Метод [Get_method_time] принимает начальный и конечный индекс диапазона измерений, которые необходимо проверить.
* Метод [Get_method_ystavka] принимает значение уставки, по которой будет определяться ток короткого замыкания.*/
@RestController
public class RestControllerIzmer implements RestControllerIzmer_Inter { //Реализация интерфейса [RestControllerIzmer_Inter]
    @Autowired //Аннотация, которая просит Spring подставить значение в поле, которое эта аннотация аннотирует
    private ServiceFilePars comtrade_file;
    @PostMapping("/data/upload") //Аннотация для принятия POST запроса
    public void Post_method(@RequestParam MultipartFile file){ //Реализация методы по парсингу принимаемого файла
        comtrade_file.parseFile(file);
    }
    @GetMapping("/data/findFault") //Аннотация для принятия GET запроса
    public String Get_method_time(@RequestParam(name = "startIndex") int startIndex, @RequestParam(name = "endIndex") int endIndex) {
        return comtrade_file.identify_KZ_time(startIndex, endIndex); //Реализация метода по определеннию КЗ на выбранном диапазоне
    }
    @GetMapping("/saveSetPoint") //Аннотация для принятия GET запроса
    public void Get_method_ystavka(@RequestParam(name = "setPoint") double setPoint) { //Реализация метода по заданию уставки тока
        comtrade_file.identify_KZ_ystavka(setPoint);
    }
}

/*В test/schema.sql находится шаблон базы данных, куда будут записываться принимаемые значения.*/
