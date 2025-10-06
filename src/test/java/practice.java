import Utilities.ExcelUtilities;

import java.io.IOException;

public class practice {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir")+"\\testData\\addPlaceData.xlsx";
        String sheetName = "Places";
        ExcelUtilities excel = new ExcelUtilities(path, sheetName);
        int rowCount = excel.getRowCount();
        int cellCount = excel.getCellCount(1);
        for (int i = 1 ; i < rowCount ; i++) {
            for (int j = 0 ; j < cellCount ; j++) {
                System.out.print(excel.getCellValue(i, j)+" | ");
            }
            System.out.println();
        }
    }
}
