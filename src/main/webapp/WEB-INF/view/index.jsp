<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navigation Bar and Table Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom CSS */
        .nav-link {
            color: #ffffff; /* White color for navigation links */
        }
        .navbar-brand {
            color: #ffffff; /* White color for brand */
        }
        .nav-item {
            margin-right: 20px; /* Add some margin between navigation items */
        }
        .table th, .table td {
            vertical-align: middle; /* Center align content vertically */
        }
    </style>
</head>
<body style="background-color: #f0f0f0;"> <!-- Light gray background color -->

<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <!-- Brand -->
        <a class="navbar-brand" href="#">Logo</a>
        <!-- Toggler/collapsible Button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <!-- Navigation Links -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Menu 1</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Menu 2</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Menu 3</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">로그인</a>
                </li>
            </ul>
            <!-- User Info Button -->
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">User Info</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Table Form -->
<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead class="bg-navy text-white"> <!-- Navy background color for table header -->
                <tr>
                    <th>Column 1</th>
                    <th>Column 2</th>
                    <th>Column 3</th>
                    <th>Column 4</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Data 1</td>
                    <td>Data 2</td>
                    <td>Data 3</td>
                    <td>Data 4</td>
                </tr>
                <tr>
                    <td>Data 5</td>
                    <td>Data 6</td>
                    <td>Data 7</td>
                    <td>Data 8</td>
                </tr>
                <!-- Add more rows as needed -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Bootstrap JS (jQuery is required) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
