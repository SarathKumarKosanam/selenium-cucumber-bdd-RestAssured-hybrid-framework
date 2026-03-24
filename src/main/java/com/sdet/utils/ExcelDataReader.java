package com.sdet.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataReader {
    private static Workbook workbook;

    private static void loadWorkbook() {
        try {
            FileInputStream fis = new FileInputStream(ConfigReader.get("excel.path"));
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException("Excel file not found: " + e.getMessage());
        }
    }

    /**
     * Returns all rows from a sheet as a list of maps
     * Key = column header, Value = cell value
     */
    public static List<Map<String, String>> getSheetData(String sheetName) {
        loadWorkbook();
        Sheet sheet = workbook.getSheet(sheetName);
        List<Map<String, String>> data = new ArrayList<>();

        Row headerRow = sheet.getRow(0);
        int colCount = headerRow.getLastCellNum();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> rowData = new LinkedHashMap<>();
            for (int j = 0; j < colCount; j++) {
                String header = headerRow.getCell(j).getStringCellValue();
                Cell cell = row.getCell(j);
                String value = (cell == null) ? "" :
                        new DataFormatter().formatCellValue(cell);
                rowData.put(header, value);
            }
            data.add(rowData);
        }
        return data;
    }

    /**
     * Returns a single cell value by sheet, row index, column header
     */
    public static String getCellValue(String sheetName, int rowIndex, String columnName) {
        List<Map<String, String>> data = getSheetData(sheetName);
        return data.get(rowIndex).get(columnName);
    }
}