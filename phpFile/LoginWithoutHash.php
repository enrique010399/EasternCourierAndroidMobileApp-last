<?php
    $con = mysqli_connect("easterncourier.000webhostapp.com", "id7017472_easterncourier010399", "Anonxkeyscore012495", "id7017472_easterncourierdb");
    
    $accountUserName = $_POST["accountUserName"];
    $accountPassword = $_POST["accountPassword"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM accounts WHERE accounrUserName = ? AND accountPassword = ?");
    mysqli_stmt_bind_param($statement, "ss", $accountUsername, $accountPassword);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $accountUsername, $accountPassword);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        
    }
    
    echo json_encode($response);
?>