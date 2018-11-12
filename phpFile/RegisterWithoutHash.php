<?php
    $con = mysqli_connect("localhost", "id7017472_easterncourier010399", "Anonxkeyscore012495", "id7017472_easterncourierdb");
    
    $accountUserName = $_POST["accountUserName"];
    $accountPassword = $_POST["accountPassword"];
    $accountFirstName = $_POST["accountFirstName"];
    $accountLastName = $_POST["accountLastName"];
    $accountBirthDate = $_POST["accountBirthDate"];
    $accountCurrentLocation = $_POST["accountCurrentLocation"];
    $accountImage = $_POST["accountImage"];
    $accountType = $_POST["accountType"];


    $statement = mysqli_prepare($connect, "INSERT INTO accounts (accountUserName,accountPassword,accountFirstName,accountLastName,accountBirthDate,accountCurrentLocation,accountImage,accountType) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $accountUserName, $accountPassword, $accountFirstName, $accountLastName, $accountBirthDate, $accountCurrentLocation, $accountImage, $accountType);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>