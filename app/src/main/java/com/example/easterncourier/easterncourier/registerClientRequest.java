package com.example.easterncourier.easterncourier;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class registerClientRequest extends StringRequest {
    private static final String register_client_request_url = "https://easterncourier.000webhostapp.com/RegisterWithoutHash.php";
    private Map<String, String> params;

    public registerClientRequest(String accountUserName, String accountPassword, String accountFirstName, String accountLastName, String accountBirthDate,
            String accountCurrentLocation, String accountType, Response.Listener<String> listener) {
        super(Request.Method.POST, register_client_request_url, listener, null);
        params = new HashMap<>();
        params.put("accountUserName", accountUserName);
        params.put("accountPassword", accountPassword);
        params.put("accountFirstName", accountFirstName);
        params.put("accountLastName", accountLastName);
        params.put("accountBirthDate", accountBirthDate);
        params.put("accountCurrentLocation", accountCurrentLocation);
        params.put("accountType", accountType);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
