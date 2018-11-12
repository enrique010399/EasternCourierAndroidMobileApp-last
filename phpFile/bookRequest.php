<?php
    $connect = mysqli_connect("localhost", "id7017472_easterncourier010399", "easterncourier010399", "id7017472_easterncourierdb");
    
    $accountUserName = $_POST["accountUserName"];
    $accountFirstName = $_POST["accountFirstName"];
    $accountLastName = $_POST["accountLastName"];
    $accountCurrentLocation = $_POST["accountCurrentLocation"];

    $requestPackageImage = $_POST["requestPackageImage"];
    $requestReceiverName = $_POST["requestReceiverName"];
    $requestDroppingPoint = $_POST["requestDroppingPoint"];
    $requestedDate = $_POST["requestedDate"];
    $requestDescription = $_POST["requestDescription"];


    $statement = mysqli_prepare($connect, "INSERT INTO requests (accountUserName,accountFirstName,accountLastName,accountCurrentLocation,requestPackageImage,requestReceiverName,requestDroppingPoint,requestedDate,requestDescription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" );
    mysqli_stmt_bind_param($statement, "sssssssss", $accountUserName,$accountFirstName,$accountLastName,$accountCurrentLocation,$requestPackageImage,$requestReceiverName,$requestDroppingPoint,$requestedDate,$requestDescription);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>