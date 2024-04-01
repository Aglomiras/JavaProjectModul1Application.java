package com.example.Java_Project_Modul_1.Service;

import com.example.Java_Project_Modul_1.Model.Measurement_Current;
import com.example.Java_Project_Modul_1.Repository.MeasurementsRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service //Аннотация, которая говорит, что данный класс принадлежит уровню Service
@Slf4j //Аннотация, которая создает обертку для класса (доступна благодаря библиотеки Lombok)
public class ServiceFilePars_Reliz implements ServiceFilePars { //Реализация интерфейса [ServiceFilePars]
    private double setPoint; //Создание переменной типа double с модификатором доступа private
    @Autowired //Аннотация, которая просит Spring подставить значение в поле, которое эта аннотация аннотирует
    private MeasurementsRepository repo; //Создание переменной типа MeasurementsRepository (Generics)

    @SneakyThrows
    @Override //Аннотация, которая проверяет, переопределен ли метод. Вызывает ошибку компиляции, если метод не найден в родительском классе
    public void parseFile(MultipartFile file) { //Создание метода для парсинга файла
        InputStream inputStream = file.getInputStream(); //Создаем объект для принятия потока данных. InputStream - супер класс для всех классов, которые принимают поток данных (байтовый поток)
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        //Создание класса [InputStreamReader] и передача ему потока данных с принимаемого файла. Он считывает данные и конвертирует байты в символы.
        //Используем [BufferedReader] для большей производительности. BufferedReader использует специальную область - буфер, куда складываются прочитанные символы.
        //Когда нам понадобятся символы, они будут взяты из буфера, а не напрямую из файла.
        String line = bufferedReader.readLine(); //Считывает данные целыми строками
        line = bufferedReader.readLine();

        while (line != null) { //Цикл продолжается, пока считываемая строка не будет пустой (неопределенной)
            String[] stringParts = line.split(","); //Различие значение по разделителю (запятая); деление строки на отдельные объекты
            if (stringParts.length > 3) { //Если, получившихся объектовв строке больше 3, то производим второй парсинг
                double Time = Double.parseDouble(stringParts[0]); //Возвращает новое инициализированное значение [Time], указанной строкой
                double ia = Double.parseDouble(stringParts[1]); //Возвращает новое инициализированное значение [ia], указанной строкой
                double ib = Double.parseDouble(stringParts[2]); //Возвращает новое инициализированное значение [ib], указанной строкой
                double ic = Double.parseDouble(stringParts[3]); //Возвращает новое инициализированное значение [ic], указанной строкой
                log.info("Time = {} , ia ={}, ib ={}, ic={}", Time, ia, ib, ic); //Лог с выводом в журнал (консоль) вкладываемых в базу данных данных с файла
                repo.addDatabase(new Measurement_Current(0, Time, ia, ib, ic)); //Добавление данных в базу данных, через метод репозитория
            }
            line = bufferedReader.readLine(); //Записывает поток символов в строку из буфера
        }
        bufferedReader.close(); //Закрывает поток символов (данных, байтов), вводимых в буфер
    }

    @Override //Аннотация, которая проверяет, переопределен ли метод. Вызывает ошибку компиляции, если метод не найден в родительском классе
    public String identify_KZ_time(int startTime, int finishTime) { //Создание метода для сравнивания значений токов с уставкой
        List<Measurement_Current> measurement = repo.List_Measurement(startTime, finishTime); //Достаем из базы данных заданный промежуток времени, по индексам

        List<Double> ia = measurement.stream().map(s -> s.getIa()).collect(Collectors.toList()); //Формируем мэп контейнер значений токов в фазе А с типом double и переводим контейнер в лист
        List<Double> ib = measurement.stream().map(s -> s.getIb()).collect(Collectors.toList()); //Формируем мэп контейнер значений токов в фазе B с типом double и переводим контейнер в лист
        List<Double> ic = measurement.stream().map(s -> s.getIc()).collect(Collectors.toList()); //Формируем мэп контейнер значений токов в фазе C с типом double и переводим контейнер в лист

        String Current_Proverka_KZ = ""; //Выделение места в памяти под строку (создание пустой строки)
        for (double CurrIa : ia ) { //Итерируемся по листу со значениями Ia
            if (setPoint * Math.sqrt(2) < CurrIa) { //Сравнения тока в фазе А с уставкой
                Current_Proverka_KZ += "fault in phase (A) "; //Если уставка по току превышена, добавляем сообщение (в виде строки) в пустую строку, заготовленную заранее
                break; //Выходим из цикла (прекращаем итерирование, так как нам достаточно одного превышения уставки, чтобы понять, есть ли короткое замыкание в фазе
            }
        }
        for (double CurrIb : ib ) { //Итерируемся по листу со значениями Ib
            if (setPoint * Math.sqrt(2) < CurrIb) { //Сравнения тока в фазе B с уставкой
                Current_Proverka_KZ += "fault in phase (B) "; //Если уставка по току превышена, добавляем сообщение (в виде строки) в пустую строку, заготовленную заранее
                break; //Выходим из цикла (прекращаем итерирование, так как нам достаточно одного превышения уставки, чтобы понять, есть ли короткое замыкание в фазе
            }
        }
        for (double CurrIc : ic ) {  //Итерируемся по листу со значениями Ic
            if (setPoint * Math.sqrt(2) < CurrIc) { //Сравнения тока в фазе C с уставкой
                Current_Proverka_KZ += "fault in phase (C) "; //Если уставка по току превышена, добавляем сообщение (в виде строки) в пустую строку, заготовленную заранее
                break; //Выходим из цикла (прекращаем итерирование, так как нам достаточно одного превышения уставки, чтобы понять, есть ли короткое замыкание в фазе
            }
        }
        return Current_Proverka_KZ; //Вывод получившегося сообщения с результатом проверки
    }
    @Override //Аннотация, которая проверяет, переопределен ли метод. Вызывает ошибку компиляции, если метод не найден в родительском классе
    public void identify_KZ_ystavka(double setPoint) { //Метод, принимающий значение уставки по току
        this.setPoint = setPoint;
    }
}