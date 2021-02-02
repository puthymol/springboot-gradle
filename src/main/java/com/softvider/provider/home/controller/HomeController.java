package com.softvider.provider.home.controller;

import com.softvider.provider.home.service.HomeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/home")
public class HomeController {
    private static final Logger log = LogManager.getLogger(HomeController.class);

    @Inject private HomeService homeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> Home() {
        Map<String, Object> result = homeService.Home();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
    public Map<String, Object> Home(@PathVariable(value="fileName") String fileName) {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("provider", "Softvider");
        writeLogIntoFile(jsonString, fileName);
        return jsonString;
    }

    private void writeLogIntoFile(Map<String, Object> jsonString, String fileName){
        try {
            String dir = System.getenv("CATALINA_HOME");
            File actualFile = new File(dir+"/logs/springboot-gradle", fileName+".txt");
            FileWriter myWriter = new FileWriter(actualFile);
            myWriter.write(jsonString.toString());
            myWriter.close();
            log.info("Successfully wrote to the file.");
        } catch (IOException e) {
            log.info("An error occurred.");
            e.printStackTrace();
        }
    }
}
