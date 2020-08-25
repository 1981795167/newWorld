package com.asia.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.*;

/**
 * @author Xavier.liu
 * @date 2020/7/24 16:26
 */
public class CashinExport {

    public static void main(String[] args) {
        try(
                Workbook workbook = new XSSFWorkbook(new File("D://execl//20200821150948.xls"));
        ) {
            Sheet sheet = workbook.getSheetAt(0);
            List<VmEventErrorEnty> errorEntyList =  addList(sheet);
            Set<String> set1 = new HashSet<>();
            Set<String> set2 = new HashSet<>();
            Set<String> set3 = new HashSet<>();
            Set<String> set4 = new HashSet<>();
            Set<String> set5 = new HashSet<>();
            Set<String> set6 = new HashSet<>();
            errorEntyList.forEach(vmEventErrorEnty -> {
                long day = vmEventErrorEnty.getSpanTimeDay();
                if (day < 1){
                    set1.add(vmEventErrorEnty.getDeviceId());
                }else if (day < 3){
                    set2.add(vmEventErrorEnty.getDeviceId());
                }else if (day < 7){
                    set3.add(vmEventErrorEnty.getDeviceId());

                }else if (day < 14){
                    set4.add(vmEventErrorEnty.getDeviceId());
                }else if (day < 30){
                    set5.add(vmEventErrorEnty.getDeviceId());
                }else {
                    set6.add(vmEventErrorEnty.getDeviceId());
                }

            });

            System.out.println(set1.size());
            System.out.println(set2.size());
            System.out.println(set3.size());
            System.out.println(set4.size());
            System.out.println(set5.size());
            System.out.println(set6.size());
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static List<VmEventErrorEnty> addList(Sheet sheet){
        List<VmEventErrorEnty> entyList = new ArrayList<>();
        for ( int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            String deviceId = "";
            long spanTimeDay = 0;
            for (int j = 0; j <= 11; j++){
                if (j != 0 && j != 11)
                    continue;
                Cell cell = row.getCell(j);

                if (Objects.nonNull(cell)){
                    if (j != 0)
                        spanTimeDay = (long) cell.getNumericCellValue();
                    else{
                        String a = cell.getStringCellValue();
                        deviceId = a;
                    }
                }
            }
            VmEventErrorEnty enty = VmEventErrorEnty.builder()
                    .spanTimeDay(spanTimeDay)
                    .deviceId(deviceId)
                    .build();
            entyList.add(enty);
        }
        return entyList;
    }
}
