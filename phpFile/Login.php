<?php
    require("hashPassword.php");
    $con = mysqli_connect("easterncourier.000webhostapp.com", "id7017472_easterncourier010399", "Anonxkeyscore012495", "id7017472_easterncourierdb");
    
    $accountUsername = $_POST["accountUsername"];
    $accountPassword = $_POST["accountPassword"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM accounts WHERE accountUsername = ? AND accountPassword=?");
    mysqli_stmt_bind_param($statement, "s", $accountUsername, $accountPassword);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colAccountID, $colAccountUserName, $colPassword);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password, $colPassword)) {
            $response["success"] = true;  
        }
    }
    echo json_encode($response);
?>