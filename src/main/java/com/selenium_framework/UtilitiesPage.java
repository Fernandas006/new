package com.selenium_framework;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import org.apache.logging.log4j.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilitiesPage extends BasePage{
    private static final Logger logger= LogManager.getLogger(UtilitiesPage.class);
    static LocalDateTime time=LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public UtilitiesPage(WebDriver driver){
        super(driver);
    }

    public String getPropertyFileValue(String key) {
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

    String excelFilePath=getPropertyFileValue("userDataExcel");
    public List<String> getExcelValues(String excelFilePath){
        List<String> result=new ArrayList<>();
        DataFormatter df=new DataFormatter();
        try(FileInputStream fs=new FileInputStream(excelFilePath);
        Workbook wb=new XSSFWorkbook(fs);){
            Sheet sheet=wb.getSheet("Sheet1");
            
            for(Row row:sheet){
                boolean header=true;
                if (header){
                    header=false;
                    continue;
                }
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    result.add(df.formatCellValue(row.getCell(i)));
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public void takeScreenshot(String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String timestamp = LocalDateTime.now().format(formatter);
            FileUtils.copyFile(screenshot, new File("target/screenshots/" + fileName+ timestamp + ".png"));
        } catch (IOException e) {
            logger.error("Error occured while taking screenshot",e);
            throw e;
        }
    }

}
