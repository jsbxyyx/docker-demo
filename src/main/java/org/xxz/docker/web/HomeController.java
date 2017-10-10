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
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String USR_LOCAL = "/usr/local";
    
    @RequestMapping("/writeFile")
    public String createFile() {
        FileWriter fw = null; 
        try {
            fw = new FileWriter(USR_LOCAL + "/blog");
            fw.write("blog");
            fw.flush();
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                if (fw != null)
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
            br = new BufferedReader(new FileReader(USR_LOCAL + "/blog"));
            line = br.readLine();
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }
    
    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        return USER_DIR + "|" + USER_HOME;
    }

}
