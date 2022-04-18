package com.muhammedtopgul.springexcel.helper;

import com.muhammedtopgul.springexcel.entity.StudentEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 11:20
 */

public class ExcelExporter {
    private final String[] headers = {"Id", "Name", "Address", "City", "Pin"};
    private final String sheetName = "Student";
    private final String fileHeader = "Student Information";
    private final List<StudentEntity> listOfStudent;

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelExporter(List<StudentEntity> listOfStudent) {
        this.listOfStudent = listOfStudent;
        workbook = new XSSFWorkbook();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle cellStyle) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(cellStyle);
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet(sheetName);
        Row row = sheet.createRow(0);
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        createCell(row, 0, fileHeader, cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        font.setFontHeightInPoints((short) 10);

        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        for (int column = 0; column < headers.length; column++) {
            createCell(row, column, headers[column], cellStyle);
        }
    }

    private void writeDataLines() {
        int rowCount = listOfStudent.size();

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        cellStyle.setFont(font);

        Row row;
        int columnCount;
        for (StudentEntity student : listOfStudent) {
            row = sheet.createRow(rowCount++);
            columnCount = 0;
            createCell(row, columnCount++, student.getId(), cellStyle);
            createCell(row, columnCount++, student.getName(), cellStyle);
            createCell(row, columnCount++, student.getAddress(), cellStyle);
            createCell(row, columnCount++, student.getCity(), cellStyle);
            createCell(row, columnCount, student.getPin(), cellStyle);
        }
    }

    public void exportExcel(HttpServletResponse response)  {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
