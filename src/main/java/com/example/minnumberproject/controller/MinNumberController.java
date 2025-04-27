package com.example.minnumberproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.PriorityQueue;

@RestController
public class MinNumberController {

    @PostMapping("/findNthMin")
    @Operation(
            summary = "Find Nth minimum number from XLSX file",
            parameters = {
                    @Parameter(name = "filePath", description = "Локальный путь к файлу (например, C:/data/test.xlsx)", required = true),
                    @Parameter(name = "n", description = "Порядковый номер минимального числа (N >= 1)", required = true)
            }
    )    public ResponseEntity<?> findNthMin(
            @RequestParam String filePath,
            @RequestParam int n) {

        try {
            //Валидация входных параметров
            if (n < 1) return ResponseEntity.badRequest().body("N must be >= 1");

            File file = new File(filePath);
            if (!file.exists()) return ResponseEntity.notFound().build();

            //Алгоритм поиска с max-heap
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, (a, b) -> b - a);

            try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
                workbook.getSheetAt(0).forEach(row -> {
                    int num = (int) row.getCell(0).getNumericCellValue();

                    if (maxHeap.size() < n) {
                        maxHeap.offer(num);
                    } else if (num < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.offer(num);
                    }
                });
            }

            return maxHeap.size() >= n
                    ? ResponseEntity.ok(maxHeap.peek())
                    : ResponseEntity.badRequest().body("Not enough numbers");

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}