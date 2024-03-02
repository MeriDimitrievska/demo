package mk.ukim.finki.demo.controller;
//
//import mk.ukim.finki.demo.service.CalculationService;
//import mk.ukim.finki.demo.service.CsvService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/api")
//public class FileController {
//    private final CalculationService calculationService;
//    private final CsvService csvService;
//
//    public FileController(CalculationService calculationService, CsvService csvService) {
//        this.calculationService = calculationService;
//        this.csvService = csvService;
//    }
//
//    @GetMapping
//    public String mainPage()
//    {
//        return "upload";
//    }
//
//    @PostMapping("/upload")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
//        try {
//            String content = new String(file.getBytes());
//            int sum = calculationService.calculateSum(content);
//            int traffic = calculationService.calculateTraffic(content);
//
//            model.addAttribute("sum", sum);
//            model.addAttribute("traffic", traffic);
//        } catch (Exception e) {
//            model.addAttribute("error", e.getStackTrace());
//            model.addAttribute("sum", 1);
//            model.addAttribute("traffic", 1);
//        }
//
//        return "upload";
//    }
//
//    @PostMapping("/saveCSV")
//    public String saveDataAsCSV(@RequestParam("sum") int sum, @RequestParam("traffic") int traffic) {
//        try {
//            csvService.saveAsCSV(sum, traffic); // Save sum and traffic as CSV
//            return "Data saved successfully as CSV!";
//        } catch (IOException e) {
//            return "Failed to save data as CSV: " + e.getMessage();
//        }
//    }
//}
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
@RequestMapping("/api")
public class FileController {

    @GetMapping
    public String showUploadForm(Model model) {
        return "upload"; // Return the name of the Thymeleaf template for the upload form
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        long startTime = System.currentTimeMillis();
        try {
            double fileSizeInMB = file.getSize() / (1024.0 * 1024.0); // Convert bytes to MB
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            long sum = 0;
            while ((line = reader.readLine()) != null) {
                sum += Long.parseLong(line.trim());
            }
            long endTime = System.currentTimeMillis();
            long processingTime = endTime - startTime;
            model.addAttribute("processingTime", processingTime);
            model.addAttribute("fileSizeInMB", fileSizeInMB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "upload"; // Return Thymeleaf template for displaying results
    }
}
