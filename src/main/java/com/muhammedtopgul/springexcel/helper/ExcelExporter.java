package com.muhammedtopgul.springexcel.helper;

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
import java.util.List;
import java.util.Map;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 11:20
 */

public class ExcelExporter<T> {
    private final ExcelColumnProcessor excelColumnProcessor = new ExcelColumnProcessor();
    private final String sheetName;
    private final String fileHeader;
    private final List<T> dataList;
    private final Map<String, String> fieldNames;
    private final Class<?> clazz;
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelExporter(List<T> dataList, String sheetName, String fileHeader) {
        this.sheetName = sheetName;
        this.fileHeader = fileHeader;
        this.dataList = dataList;
        workbook = new XSSFWorkbook();
        clazz = dataList.get(0).getClass();
        fieldNames = excelColumnProcessor.getFieldNamesForClass(clazz);
    }

    private <E> void createCell(Row row, int columnCount, E value, CellStyle cellStyle) {
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
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        Row row = sheet.createRow(0);
        createCell(row, 0, fileHeader, cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        font.setFontHeightInPoints((short) 10);

        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        int column = 0;
        for (Map.Entry<String, String> entry : fieldNames.entrySet()) {
            String columnHeader = entry.getValue();
            createCell(row, column, columnHeader, cellStyle);
            column++;
        }
    }

    private void writeDataLines() {
        int rowCount = dataList.size();

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        cellStyle.setFont(font);

        Row row;
        int columnCount;
        for (T data : dataList) {
            row = sheet.createRow(rowCount++);
            columnCount = 0;

            for (Map.Entry<String, String> entry : fieldNames.entrySet()) {
                String fieldName = entry.getKey();
                Object value = null;
                try {
                    value = excelColumnProcessor.invokeMethod(clazz, data, fieldName);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                createCell(row, columnCount++, value, cellStyle);
            }
        }
    }

    public void exportExcel(HttpServletResponse response) {
        try {
            writeHeaderLine();
            writeDataLines();
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
