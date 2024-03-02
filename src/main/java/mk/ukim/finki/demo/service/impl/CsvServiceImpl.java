package mk.ukim.finki.demo.service.impl;

import mk.ukim.finki.demo.service.CsvService;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class CsvServiceImpl implements CsvService {

    @Override
    public void saveAsCSV(int sum, int traffic) throws IOException {
        String fileName = "data.csv";
        String content = String.format("%d,%d", sum, traffic);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.append(content);
        }
    }
}
