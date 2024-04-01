package com.example.Java_Project_Modul_1.Controller;
import org.springframework.web.multipart.MultipartFile;

public interface RestControllerIzmer_Inter {
    public void Post_method(MultipartFile file);
    public String Get_method_time(int startIndex, int endIndex);
    public void Get_method_ystavka(double startIndex);
}
