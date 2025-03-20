                                                                   **📂 File Processing and Conversion Application**
A Spring Boot-based web application designed to handle file uploads, process file contents, and convert them into different formats. The application supports processing CSV and Excel files, as well as converting XML files to JSON. Processed data is displayed in an HTML table format, and converted JSON files are saved to a specified directory.

✨ Key Features
File Upload and Processing
Upload CSV and Excel files through a web interface.

Extract data starting from a specified row.

Display processed data in an HTML table format.

Excel File Processing
Process Excel files (.xlsx and .xls).

Extract data from specific rows and columns.

Specify a starting row and column for data extraction.

CSV File Processing
Read CSV files and extract data starting from a specified row.

Treat the first row as headers and subsequent rows as data.

XML to JSON Conversion
Upload XML files and convert them into JSON format.

Save converted JSON files to the uploads/json/ directory.

Provide the path of the saved JSON file as feedback.

User Interface
Simple web interface for file uploads.

Specify starting row and column (for Excel files).

Display processed data or conversion results on the web page.

🛠️ Technologies Used
Spring Boot: Backend framework.

Thymeleaf: HTML template rendering.

Apache POI: Excel file processing.

OpenCSV: CSV file processing.

Jackson: XML to JSON conversion.

HTML/CSS: Displaying processed data in tables.

🏗️ Project Structure
Controller (FIleUploadontroller)
Handles HTTP requests for file uploads and processing.

Routes requests to the appropriate service methods based on file type.

Passes processed data to the view (Thymeleaf template) for rendering.

Service (FileProcessingService)
Core logic for processing CSV, Excel, and XML files.

Converts XML files to JSON and saves them to the specified directory.

Formats processed data into HTML tables for display.

View (Thymeleaf Templates)
upload.html: File upload form.

uploadResult.html: Displays processed data in an HTML table.

xmlConversion.html: Displays XML to JSON conversion results.

🔧 File Processing Logic
CSV Processing
The processCsv method reads CSV files using OpenCSV.

Extracts headers from the first row and data from the specified starting row.

Formats data into an HTML table using the formatTableOutput method.

Excel Processing
The processExcel method reads Excel files using Apache POI.

Extracts headers from the first row and data from the specified starting row.

If a specific cell is requested, extracts the value from the specified cell.

Formats data into an HTML table using the formatTableOutput method.

XML to JSON Conversion
The convertXmlToJson method reads XML files using Jackson.

Converts XML content into a JSON string and saves it to the uploads/json/ directory.

Returns the path of the saved JSON file to the user.

🚨 Error Handling
Handles exceptions such as invalid file formats, missing files, and processing errors.

Displays error messages to the user in the web interface.
