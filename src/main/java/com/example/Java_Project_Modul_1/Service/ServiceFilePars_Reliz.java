package com.example.Java_Project_Modul_1.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class ServiceFilePars_Reliz implements ServiceFilePars {
    private double setPoint;

    @SneakyThrows
    @Override
    public void parseFile(MultipartFile file) {
        InputStream inputStream = file.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line = bufferedReader.readLine();
        line = bufferedReader.readLine();

        while (line != null){
            String[] stringParts = line.split(",");
            if (stringParts.length > 3){
                double Time = Double.parseDouble(stringParts[0]);
                double ia = Double.parseDouble(stringParts[1]);
                double ib = Double.parseDouble(stringParts[2]);
                double ic = Double.parseDouble(stringParts[3]);
                log.info("Time = {} , ia ={}, ib ={}, ic={}", Time, ia,ib,ic);
            }
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    @Override
    public String identify_KZ_time(int startTime, int finishTime) {
        return null;
    }
    @Override
    public void identify_KZ_ystavka(double setPoint) {
        this.setPoint = setPoint;
    }
}