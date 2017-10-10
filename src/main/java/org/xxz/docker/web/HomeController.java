package org.xxz.docker.web;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @RequestMapping("/createFile")
    public String createFile() {
        final String userDir = System.getProperty("user.dir");
        FileWriter fw = null; 
        try {
            fw = new FileWriter(userDir + "/blog");
            fw.write("blog");
            fw.flush();
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userDir;
    }

}
