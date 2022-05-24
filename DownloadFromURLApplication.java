package com.example.DownloadFromURL;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;

import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class DownloadFromURLApplication {
	// private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Scheduled(cron = "0 0 6 * * ?") //每天早上六點觸發
	public static void main(String[] args) {
		SpringApplication.run(DownloadFromURLApplication.class, args);
		LocalDate todaysDate = LocalDate.now();
		String add = downloadFromUrl("https://www.good.nat.gov.tw/regcenter/csv/"+todaysDate.minusDays(1L)+"-new-addressbook.csv","D:");
		String sub = downloadFromUrl("https://www.good.nat.gov.tw/regcenter/csv/"+todaysDate.minusDays(1L)+"-new-subrogation.csv","D:");  
		System.out.println(add+sub);
	//	logger.info("簡單的日誌記錄測試 :  {}  + {}  =  {}");
		
	} 
	 public static String downloadFromUrl(String url,String dir) {  
		  
	        try {  
	            URL httpurl = new URL(url);  
	            String fileName = getFileNameFromUrl(url);  
	            System.out.println(fileName);  
	            File f = new File(dir + fileName);  
	            FileUtils.copyURLToFile(httpurl, f);  
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	         
	          //  return ResponseEntity.status(HttpStatus.OK).body(todoList);
	            return "Fault!";
	        }   
	        return "Successful!";  
	    }  
	      
	    public static String getFileNameFromUrl(String url){  
	        String name = new Long(System.currentTimeMillis()).toString() + ".X";  
	        int index = url.lastIndexOf("/");  
	        if(index > 0){  
	            name = url.substring(index + 1);  
	            if(name.trim().length()>0){  
	                return name;  
	            }  
	        }  
	        return name;  
	    }  
}

