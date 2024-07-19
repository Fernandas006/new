package com.selenium_framework;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class UtilitiesPage {
    public static String getPropertyFileValue(String key) {
        String value="";
        Properties pt=new Properties();
        try(FileInputStream fs=new FileInputStream("src\\main\\resources\\config.properties")){
            pt.load(fs);
            value=pt.getProperty(key);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return value;
    }

    String excelFilePath=UtilitiesPage.getPropertyFileValue("userDataExcel");
    public List<String> getExcelValues(String excelFilePath){
        List<String> result=new ArrayList<>();
        DataFormatter df=new DataFormatter();
        try(FileInputStream fs=new FileInputStream(excelFilePath);
        Workbook wb=new XSSFWorkbook();){
            Sheet sheet=wb.getSheetAt(0);
            for(Row row:sheet){
                result.add(df.formatCellValue(row.getCell(0)));
                result.add(df.formatCellValue(row.getCell(1)));
                result.add(df.formatCellValue(row.getCell(2)));
                result.add(df.formatCellValue(row.getCell(3)));
                result.add(df.formatCellValue(row.getCell(4)));
                result.add(df.formatCellValue(row.getCell(5)));
                result.add(df.formatCellValue(row.getCell(6)));
                result.add(df.formatCellValue(row.getCell(7)));
                result.add(df.formatCellValue(row.getCell(8)));
                result.add(df.formatCellValue(row.getCell(9)));
                result.add(df.formatCellValue(row.getCell(10)));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }



}
