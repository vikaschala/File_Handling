<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>File Upload</title>
    <style>
        /* General Styles */
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 2rem auto;
            padding: 1rem;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            background-color: white;
        }
        .card-body {
            padding: 2rem;
        }
        h1 {
            color: #333;
            font-weight: bold;
            text-align: center;
            margin-bottom: 1.5rem;
        }
        .form-label {
            font-weight: bold;
            color: #555;
        }
        .form-control {
            border-radius: 5px;
            border: 1px solid #ddd;
            padding: 0.75rem;
            margin-bottom: 1rem;
            width: 100%;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            padding: 0.75rem;
            font-size: 1rem;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            width: 100%;
            color: white;
            cursor: pointer;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .row {
            display: flex;
            gap: 1rem;
            margin-bottom: 1rem;
        }
        .col-md-6 {
            flex: 1;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📂 File Processing and Conversion</h1>
        <div class="card">
            <div class="card-body">
                <form action="/upload" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="file" class="form-label">Upload File</label>
                        <input type="file" class="form-control" id="file" name="file" required>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="startRow" class="form-label">Start Row</label>
                            <input type="number" class="form-control" id="startRow" name="startRow" value="1" min="1">
                        </div>
                        <div class="col-md-6">
                            <label for="startCol" class="form-label">Start Column (Excel Only)</label>
                            <input type="number" class="form-control" id="startCol" name="startCol" value="1" min="1">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Process File</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
