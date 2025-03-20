package com.fp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fp.service.FileProcessingService;

@Controller
@RequestMapping("/files")
public class FIleUploadontroller {
	
	@Autowired
	private FileProcessingService fileService;
	

	
	//to show upload page
	@GetMapping("/upload")
	public String showUploadpage() {
		return"upload";
	}
	
	//handle upload post request
	@PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, 
                             @RequestParam("startRow") int startRow,
                             @RequestParam("col") int col, 

                             Model model) {
		System.out.println(col);
        String fileName = file.getOriginalFilename();

        if (fileName == null) {
            model.addAttribute("message", "Invalid file.");
            return "uploadResult";
        }

        try {
            String tableHtml;
            if (fileName.endsWith(".csv")) {
                tableHtml = fileService.processCsv(file, startRow);
            }
            else if (fileName.endsWith(".xlsx")||fileName.endsWith(".xls")&&col>0) {
                tableHtml = fileService.processExcel(file, startRow, col);
            }
            else if (fileName.endsWith(".xlsx")||fileName.endsWith(".xls")&&col==0) {
            	System.out.println("method executed");
                tableHtml = fileService.processExcel(file, startRow);
            }
                
             else {
                model.addAttribute("message", "Unsupported file format.");
                return "uploadResult";
            }

            model.addAttribute("tableData", tableHtml);
            return "uploadResult"; // Forward to a Thymeleaf
        } catch (Exception e) {
            model.addAttribute("message", "Error processing file: " + e.getMessage());
            return "uploadResult";
        }
    }
	
	@PostMapping("/upload/xml")
	public String convertXmltoJson(@RequestParam("file") MultipartFile file,Model model) {
		try {
			String convertXmlToJson = fileService.convertXmlToJson(file);
			model.addAttribute("msg", convertXmlToJson);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return"xmlConversion";
	}


}
