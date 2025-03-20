🚀 File Processing and Conversion Application
A Spring Boot-based web application designed to handle file uploads, process file contents, and convert them into different formats. The application supports processing CSV and Excel files, as well as converting XML files to JSON. Processed data is displayed in an HTML table format, and converted JSON files are saved to a specified directory.

✨ Key Features
File Upload and Processing
Users can upload CSV and Excel files through a web interface.

The application processes the uploaded files and extracts data starting from a specified row.

The processed data is displayed in an HTML table format on the web page.

Excel File Processing
The application can process Excel files (.xlsx and .xls) and extract data from specific rows and columns.

Users can specify the starting row for data extraction.

If a specific column is provided, the application extracts the value from the specified cell.

CSV File Processing
The application reads CSV files and extracts data starting from a specified row.

The first row of the CSV file is treated as headers, and the subsequent rows are treated as data.

XML to JSON Conversion
Users can upload XML files, and the application converts them into JSON format.

The converted JSON file is saved in a predefined directory (uploads/json/).

The application provides the path of the saved JSON file as feedback to the user.

User Interface
The application provides a simple web interface for file uploads.

Users can select a file, specify the starting row, and (for Excel files) specify a column number.

The processed data or conversion result is displayed on the web page.

🛠️ Technical Details
Technologies Used
Spring Boot: Framework for backend development.

Thymeleaf: Used for rendering HTML templates and displaying processed data.

Apache POI: Library for reading and processing Excel files.

OpenCSV: Library for reading and processing CSV files.

Jackson: Library for XML to JSON conversion.

HTML/CSS: Used for displaying the processed data in a table format.

🏗️ Project Structure
Controller (FIleUploadontroller)
Handles HTTP requests for file uploads and processing.

Routes requests to the appropriate service methods based on the file type.

Passes the processed data to the view (Thymeleaf template) for rendering.

Service (FileProcessingService)
Contains the core logic for processing CSV, Excel, and XML files.

Converts XML files to JSON and saves them to the specified directory.

Formats the processed data into HTML tables for display.

View (Thymeleaf Templates)
upload.html: The file upload form where users can select files and specify parameters.

uploadResult.html: Displays the processed data in an HTML table.

xmlConversion.html: Displays the result of XML to JSON conversion.

🔧 File Processing Logic
CSV Processing
The processCsv method reads the CSV file using OpenCSV.

It extracts headers from the first row and data from the specified starting row.

The data is formatted into an HTML table using the formatTableOutput method.

Excel Processing
The processExcel method reads the Excel file using Apache POI.

It extracts headers from the first row and data from the specified starting row.

If a specific cell is requested, the processExcel method with row and column parameters is used to extract the cell value.

The data is formatted into an HTML table using the formatTableOutput method.

XML to JSON Conversion
The convertXmlToJson method reads the XML file using Jackson.

It converts the XML content into a JSON string and saves it to the uploads/json/ directory.

The path of the saved JSON file is returned to the user.

🚨 Error Handling
The application handles exceptions such as invalid file formats, missing files, and processing errors.

Error messages are displayed to the user in the web interface.

🖥️ How It Works
Uploading a File
The user navigates to the upload page (/files/upload).

The user selects a file (CSV, Excel, or XML) and specifies the starting row (and column for Excel files).

The file is uploaded to the server.
