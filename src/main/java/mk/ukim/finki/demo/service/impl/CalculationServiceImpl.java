package mk.ukim.finki.demo.service.impl;

import mk.ukim.finki.demo.service.CalculationService;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {
    @Override
    public int calculateSum(String content) {
        String[] numbers = content.split("\\r?\\n"); // Split by new lines
        int sum = 0;
        for (String num : numbers) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }

    @Override
    public int calculateTraffic(String content) {
//        REFACTOR THIS !!!!
        String[] numbers = content.split("\\r?\\n");
        return numbers.length; // Traffic as the number of elements
    }
}
