package com.example.Java_Project_Modul_1.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ServiceFilePars {
    void parseFile(MultipartFile file);
    String identify_KZ_time(int startIndex, int endIndex);
    void identify_KZ_ystavka(double setPoint);
}