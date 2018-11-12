<?php
    require("password.php");
    $connect = mysqli_connect("easterncourier.000webhostapp.com", "id7017472_easterncourier010399", "Anonxkeyscore012495", "id7017472_easterncourierdb");
    
    $accountUserName = $_POST["accountUserName"];
    $accountPassword = $_POST["accountPassword"];
    $accountFirstName = $_POST["accountFirstName"];
    $accountLastName = $_POST["accountLastName"];
    $accountBirthDate = $_POST["accountBirthDate"];
    $accountCurrentLocation = $_POST["accountCurrentLocation"];
    $accountImage = $_POST["accountImage"];
    $accountCurrentLocation = $_POST["accountType"];


     function registerUser() {
        global $connect, $name, $age, $username, $password;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO accounts (accountUserName,accountPassword,accountFirstName,accountLastName,accountBirthDate,accountCurrentLocation,accountImage,accountType) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "siss", $name, $age, $username, $passwordHash);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
    function usernameAvailable() {
        global $connect, $username;
        $statement = mysqli_prepare($connect, "SELECT * FROM accounts WHERE username = ?"); 
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }
    $response = array();
    $response["success"] = false;  
    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
    echo json_encode($response);
?>