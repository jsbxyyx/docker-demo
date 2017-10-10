package org.xxz.docker.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    private static final String USER_DIR = System.getProperty("user.dir");
    
    @RequestMapping("/createFile")
    public String createFile() {
        FileWriter fw = null; 
        try {
            fw = new FileWriter("/blog");
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
        return USER_DIR;
    }
    
    @RequestMapping("/readFile")
    public String readFile() {
        String line = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/blog"));
            line = br.readLine();
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }
    
    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        return USER_DIR;
    }

}
