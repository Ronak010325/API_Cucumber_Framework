package Utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtilities {
    XSSFWorkbook wo;
    XSSFSheet sh;
    XSSFRow ro;
    XSSFCell ce;
    String path;
    String sheet;
    public ExcelUtilities(String filePath, String SheetName) throws IOException {
        path = filePath;
        sheet = SheetName;
    }

    public int getRowCount() throws IOException {
        wo = new XSSFWorkbook(path);
        sh = wo.getSheet(sheet);
        int rowCount = sh.getLastRowNum();
        wo.close();
        return rowCount + 1;
    }

    public int getCellCount(int rowNum) throws IOException {
        wo = new XSSFWorkbook(path);
        sh = wo.getSheet(sheet);
        ro = sh.getRow(rowNum);
        int cellCount = ro.getLastCellNum();
        wo.close();
        return cellCount;
    }

    public String getCellValue(int rowNum, int cellNum) throws IOException {
        wo = new XSSFWorkbook(path);
        sh = wo.getSheet(sheet);
        ro = sh.getRow(rowNum);
        ce = ro.getCell(cellNum);
        String output;
        try {
            DataFormatter formatter = new DataFormatter();
            output = formatter.formatCellValue(ce);
        } catch (Exception e) {
            output = "";
        }
        return output;
    }
}
