package com.softvider.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softvider.test.model.Book;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class JavaTest {

    @Test
    public void ClassToString() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            Book cleanCode = new Book("Clean Code", null, 42);

            String jsonString = mapper.writeValueAsString(cleanCode);
            JSONObject jsonObject =  mapper.readValue(jsonString, JSONObject.class);

            System.out.println(jsonString);
            System.out.println(jsonObject);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void StringToJsonNode() {
        try {
            String json = "emqsess=5cd6c3ad-53c8-402b-b0ba-418d817720da; Version=1; Expires=Sat, 24-Oct-2020 03:07:27 GMT; Max-Age=86400; Path=/";
            String[] split = json.split(";");
            String expireDateStr = null;
            for(String str : split){
                if(str.contains("Expires")) {
                    String[] sl = str.split("=");
                    expireDateStr = sl[sl.length-1];
                }
            }
            SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd-MMM-yyy HH:mm:ss zzz");
            Date d1 = sdf3.parse(expireDateStr);
            System.out.println(d1.getTime());
            System.out.println(new Date().getTime());
            System.out.println(d1.getTime() - new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
