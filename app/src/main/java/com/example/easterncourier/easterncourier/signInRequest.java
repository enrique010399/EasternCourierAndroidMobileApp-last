package com.example.easterncourier.easterncourier;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class signInRequest extends StringRequest{
    private static final String signIn_request_url = "https://easterncourier.000webhostapp.com/LoginWithoutHash.php";
    private Map<String, String> params;

    public signInRequest(String accountUserName, String accountPassword, Response.Listener<String> listener) {
        super(Request.Method.POST, signIn_request_url, listener, null);
        params = new HashMap<>();
        params.put("accountUserName", accountUserName);
        params.put("accountPassword", accountPassword);



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
