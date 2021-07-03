import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPoi {
    @Test
    public void testRead() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook("D:\\360MoveData\\Users\\Administrator\\Desktop\\JAVAee2021-4-17\\预习_day51_传智健康_笔记&代码&资料\\04_资料\\read.xlsx");

        XSSFSheet sheet = wb.getSheetAt(0);

        for (Row cells : sheet) {

            for (Cell cell : cells) {

                String value = cell.getStringCellValue();

                System.out.println("value = " + value);
            }
        }
        wb.close();
    }

    @Test
    public void testWrite() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet = wb.createSheet("yangyang");

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("ID");
        row.createCell(2).setCellValue("爱好");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("1001");
        row1.createCell(2).setCellValue("唱歌");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("2");
        row2.createCell(1).setCellValue("1002");
        row2.createCell(2).setCellValue("跳舞");

        FileOutputStream fs = new FileOutputStream("D:\\360MoveData\\Users\\Administrator\\Desktop\\JAVAee2021-4-17\\预习_day51_传智健康_笔记&代码&资料\\04_资料\\yangyang.xlsx");

        wb.write(fs);

        fs.flush();
        fs.close();
        wb.close();
    }
}
