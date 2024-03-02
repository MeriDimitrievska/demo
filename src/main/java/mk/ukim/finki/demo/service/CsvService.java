package mk.ukim.finki.demo.service;

import java.io.IOException;

public interface CsvService {
    void saveAsCSV(int sum, int traffic) throws IOException;
}
