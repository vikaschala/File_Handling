package com.fp.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class FileProcessingService {
	
    private static final String OUTPUT_DIRECTORY = "uploads/json/";


    public String processCsv(MultipartFile file, int startRow) throws IOException, CsvValidationException {
        List<String[]> data = new ArrayList<>();
        String[] headers = null;

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            int rowIndex = 0;
            while ((nextLine = reader.readNext()) != null) {
                if (rowIndex == 0) {
                    headers = nextLine; // Store headers separately
                } else if (rowIndex >= startRow) {
                    data.add(nextLine);
                }
                rowIndex++;
            }
        }

        return formatTableOutput(headers, data);
    }

    public String processExcel(MultipartFile file, int startRow) throws IOException {
        List<List<String>> data = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<String> headers = new ArrayList<>();

        for (Row row : sheet) {
        	
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
      
                rowData.add(cell.toString());
            }

            if (row.getRowNum() == 0) {
                headers = rowData; // Store the first row as headers
            } else if (row.getRowNum() >= startRow) {
                data.add(rowData);
            }
        }

        workbook.close();
        return formatTableOutput(headers, data);
    }

    private String formatTableOutput(String[] headers, List<String[]> data) {
        StringBuilder output = new StringBuilder("<table border='1'>");

        // Add headers
        if (headers != null) {
            output.append("<tr>");
            for (String col : headers) {
                output.append("<th>").append(col).append("</th>");
            }
            output.append("</tr>");
        }

        // Add data rows
        for (String[] row : data) {
            output.append("<tr>");
            for (String col : row) {
                output.append("<td>").append(col).append("</td>");
            }
            output.append("</tr>");
        }

        output.append("</table>");
        return output.toString();
    }
    public String processExcel(MultipartFile file, int rowNumber, int columnNumber) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        String cellValue = "Cell not found";

        // Get the specific row
        Row row = sheet.getRow(rowNumber);
        if (row != null) {
            // Get the specific column (cell)
            Cell cell = row.getCell(columnNumber);
            if (cell != null) {
                cellValue = cell.toString();
            }
        }

        workbook.close();
        return formatSingleCellOutput(rowNumber, columnNumber, cellValue);
    }
    private String formatSingleCellOutput(int rowNumber, int columnNumber, String cellValue) {
        return "<table border='1'>" +
                "<tr><th>Row</th><th>Column</th><th>Value</th></tr>" +
                "<tr><td>" + rowNumber + "</td><td>" + columnNumber + "</td><td>" + cellValue + "</td></tr>" +
                "</table>";
    }


    private String formatTableOutput(List<String> headers, List<List<String>> data) {
        StringBuilder output = new StringBuilder("<table border='1'>");

        // Add headers
        if (!headers.isEmpty()) {
            output.append("<tr>");
            for (String col : headers) {
                output.append("<th>").append(col).append("</th>");
            }
            output.append("</tr>");
        }

        // Add data rows
        for (List<String> row : data) {
            output.append("<tr>");
            for (String col : row) {
                output.append("<td>").append(col).append("</td>");
            }
            output.append("</tr>");
        }

        output.append("</table>");
        return output.toString();
    }
    public String convertXmlToJson(MultipartFile file) throws IOException {
        // Ensure directory exists
        File outputDir = new File(OUTPUT_DIRECTORY);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // Read XML content and convert to JSON
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(file.getInputStream());

        // Convert JsonNode to a JSON string
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonString = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

        // Define the JSON file name and save location
        String jsonFileName = file.getOriginalFilename().replace(".xml", ".json");
        File jsonFile = new File(Paths.get(OUTPUT_DIRECTORY, jsonFileName).toString());

        // Save JSON content to file
        jsonMapper.writeValue(jsonFile, jsonNode);

        return "JSON file saved at: " + jsonFile.getAbsolutePath();
    }
}
