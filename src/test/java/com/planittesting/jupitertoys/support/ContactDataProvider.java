package com.planittesting.jupitertoys.support;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContactDataProvider {

    private static String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData.csv";

    @DataProvider(name = "contactData")
    public static Object[][] feedData() throws IOException {
        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
        //read the first line
        file.readLine();
        while((record = file.readLine()) != null){
            String fields[] = record.split(",");
            records.add(fields);
        }
        file.close();

        Object[][] results = new Object[records.size()][];
        for(int i = 0; i < records.size(); i++){
            results[i]=records.get(i);
        }
        return results;
    }
}

