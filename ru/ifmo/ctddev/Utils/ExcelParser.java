package ru.ifmo.ctddev.utils;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ExcelParser {

    public static void parse(String fileName) {
        //инициализируем потоки
        String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //разбираем первый лист входного файла на объектную модель
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        //проходим по всему листу
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                //перебираем возможные типы ячеек
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        hashSet.add(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        result += "[" + cell.getNumericCellValue() + "]";
                        break;

                    case Cell.CELL_TYPE_FORMULA:
                        result += "[" + cell.getNumericCellValue() + "]";
                        break;
                    default:
                        result += "|";
                        break;
                }
            }
        }
        sheet = workBook.getSheetAt(1);
        it = sheet.iterator();
        int k = 0;
        int rowNum = 0;
        String shift1="";
        XSSFFont font = workBook.createFont();
        // указываем, что хотим его видеть жирным
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        // создаем стиль для ячейки
        XSSFCellStyle style = workBook.createCellStyle();
        // и применяем к этому стилю жирный шрифт
        style.setFont(font);
        //проходим по всему листу
        rowNum = -1;
        while (it.hasNext()) {
          rowNum++;
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            k = 0;
            String shift = "";
            while (cells.hasNext()) {
                Cell cell = cells.next();
                if (k == 0) {
                    shift = cell.getStringCellValue();
                    k++;
                } else {
                    if (k == 1) {
                        shift1 = cell.getStringCellValue();
                        k++;
                    } else {
                        //перебираем возможные типы ячеек
                        if (hashSet.contains(cell.getStringCellValue())) {
                            /*
                            Row row1 = sheet1.createRow(rowNum);
                            row1.createCell(0).setCellValue(shift);
                            row1.createCell(1).setCellValue(shift1);
                            row1.createCell(2).setCellValue(cell.getStringCellValue());
                            Cell cell1 = cells.next();
                            row1.createCell(3).setCellValue(cell1.getNumericCellValue());
                            Cell cell2 = cells.next();
                            row1.createCell(4).setCellValue(cell2.getNumericCellValue());
                            */
                            row.getCell(0).setCellStyle(style);
                            hashSet2.add(shift);
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }

        }

        Sheet sheet2 = workBook.getSheetAt(2);
        it = sheet2.iterator();

         k = 0;
         rowNum = 0;
        //проходим по всему листу
        rowNum = -1;
        while (it.hasNext()) {
            rowNum++;
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            k = 0;
            String shift = "";
            while (cells.hasNext()) {
                Cell cell = cells.next();
                Cell cell1 = cells.next();
                        if (hashSet2.contains(cell1.getStringCellValue())) {
                            /*
                            Row row1 = sheet1.createRow(rowNum);
                            row1.createCell(0).setCellValue(shift);
                            row1.createCell(1).setCellValue(shift1);
                            row1.createCell(2).setCellValue(cell.getStringCellValue());
                            Cell cell1 = cells.next();
                            row1.createCell(3).setCellValue(cell1.getNumericCellValue());
                            Cell cell2 = cells.next();
                            row1.createCell(4).setCellValue(cell2.getNumericCellValue());
                            */
                            row.getCell(1).setCellStyle(style);
                            break;
                        } else {
                            break;
                        }
                    }
                }

        try (FileOutputStream out = new FileOutputStream(new File("File1.xlsx"))) {
            workBook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


