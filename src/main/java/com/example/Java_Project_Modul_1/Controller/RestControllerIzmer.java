package com.example.Java_Project_Modul_1.Controller;

import com.example.Java_Project_Modul_1.Service.ServiceFilePars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestControllerIzmer implements RestControllerIzmer_Inter {
    @Autowired
    private ServiceFilePars comtrade_file;

    @PostMapping("/data/upload")
    public void Post_method(@RequestParam MultipartFile file){
        comtrade_file.parseFile(file);
    }
    @GetMapping("/data/findFault")
    public String Get_method_time(@RequestParam(name = "startIndex") int startIndex, @RequestParam(name = "endIndex") int endIndex) {
        return comtrade_file.identify_KZ_time(startIndex, endIndex);
    }
    @GetMapping("/saveSetPoint")
    public void Get_method_ystavka(@RequestParam(name = "startIndex") double setPoint) {
        comtrade_file.identify_KZ_ystavka(setPoint);
    }
}
