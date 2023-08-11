package com.java.assignment.assignment.controller;

import java.util.Arrays;
import java.util.List;

// import java.net.http.HttpHeaders;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.java.assignment.assignment.helper.CustomerData;
import com.java.assignment.assignment.helper.customerDataWithuuid;
import com.java.assignment.assignment.helper.loginHelper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Controller
public class loginController {
    String bearerToken="";
    @GetMapping("/")
    private String logInController(){
        System.out.println("running login controller");
        return "log-in";
    }
    @PostMapping("/authenticate")
    public String authenticateUser(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            String authUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

            JSONObject jsonInput = new JSONObject();
            jsonInput.put("login_id", username);
            jsonInput.put("password", password);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonInput.toString(), headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> authResponse = restTemplate.exchange(authUrl, HttpMethod.POST, requestEntity, String.class);

            if (authResponse.getStatusCode() == HttpStatus.OK) {
                JSONObject jsonResponse = new JSONObject(authResponse.getBody());
                bearerToken = jsonResponse.getString("access_token");
                System.out.println(bearerToken);
                if (!bearerToken.isEmpty()) {
                    // model.addAttribute("token", bearerToken);
                    return "redirect:/user-dashboared";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "log-in";
    }


    @GetMapping("/user-dashboared")
    public String dashboaredController(Model model){
        try{
           
            System.out.println("running dashboared");
        String createUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
        String cmd = "get_customer_list";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + bearerToken);
        
               HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<customerDataWithuuid[]> response = restTemplate.exchange(createUrl+"?cmd="+cmd, HttpMethod.GET, requestEntity, customerDataWithuuid[].class);
        System.out.println("token is "+ bearerToken);
        // model.addAttribute("token", bearerToken);
        System.out.println(response.getBody());
        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("token", bearerToken);
            List<customerDataWithuuid> customerList = Arrays.asList(response.getBody());
            model.addAttribute("customerList", customerList);
              for(customerDataWithuuid p:customerList){
                System.out.println(p);
              }
            
            model.addAttribute("result", "Successfully Created");
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            model.addAttribute("result", "Error Occurred");
        } else {
            model.addAttribute("result", "Error occurred");
        }
    
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("result", "Error occurred");
    }
        return "dashboared";
    }


    @GetMapping("create-account")
    public String sendingview(Model model){
model.addAttribute("token", bearerToken);
customerDataWithuuid cd=new customerDataWithuuid();
model.addAttribute("user", cd);
        return "create-account";
    }
    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute CustomerData customerData,
                                 @RequestParam String token,
                                 Model model) {

        try {
            if(customerData.getFirst_name().trim().isEmpty()||customerData.getLast_name().trim().isEmpty()){
                model.addAttribute("result","first name and last name are mandatory");
                // throw new Exception("first name and last name are mandatory");

            }else{
            String createUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
            String cmd = "create";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);
            JSONObject requestData = new JSONObject();
            // requestData.put("cmd", cmd);
            requestData.put("first_name", customerData.getFirst_name());
            requestData.put("last_name", customerData.getLast_name());
            requestData.put("street", customerData.getStreet());
            requestData.put("address", customerData.getAddress());
            requestData.put("city", customerData.getCity());
            requestData.put("email", customerData.getEmail());
            requestData.put("phone", customerData.getPhone());
            // requestData.put("last_name", customerData.getLast_name());
            // requestData.put("last_name", customerData.getLast_name());
            System.out.println(customerData);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestData.toString(), headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(createUrl+"?cmd="+cmd, HttpMethod.POST, requestEntity, String.class);
           
            if (response.getStatusCode() == HttpStatus.CREATED) {
                model.addAttribute("result", "Successfully Created");
            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                model.addAttribute("result", "First Name or Last Name is missing");
            } else {
                model.addAttribute("result", "Error occurred");
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("result", "Error occurred");
        }
    

        return "createCustomer";
    }



    @GetMapping("user-delete")
private String deleteUser(@RequestParam("user-id") String id ,Model model){

    try{
           
        System.out.println("running dashboared");
    String createUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    String cmd = "delete";
    String uuid=id;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + bearerToken);
    
           HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.exchange(createUrl+"?cmd="+cmd+"&uuid="+uuid, HttpMethod.POST, requestEntity, String.class);
    System.out.println("token is "+ bearerToken);
    // model.addAttribute("token", bearerToken);
    System.out.println(response.getStatusCode());
    if (response.getStatusCode() == HttpStatus.OK) {
        model.addAttribute("token", bearerToken);
        model.addAttribute("result", "Successfully Created");
    } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
        model.addAttribute("result", "Error Not deleted");
    } else if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
        model.addAttribute("result", "UUID not Found");
    }

} catch (Exception e) {
    e.printStackTrace();
    model.addAttribute("result", "Error occurred");
}
    return "redirect:/user-dashboared";
}


@GetMapping("user-update")
private String updateUser(@RequestParam("user-id") String id ,Model model){

    try{
           
        System.out.println("running dashboared");
        System.out.println("running dashboared");
        String createUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
        String cmd = "get_customer_list";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + bearerToken);
        
               HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<customerDataWithuuid[]> response = restTemplate.exchange(createUrl+"?cmd="+cmd, HttpMethod.GET, requestEntity, customerDataWithuuid[].class);
        System.out.println("token is "+ bearerToken);
        // model.addAttribute("token", bearerToken);
        System.out.println(response.getBody());
        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("token", bearerToken);
            List<customerDataWithuuid> customerList = Arrays.asList(response.getBody());
            // model.addAttribute("customerList", customerList);
              for(customerDataWithuuid p:customerList){
                if(p.getUuid().trim().equals(id)){
                    model.addAttribute("user", p);
                    break;
                }
              }
            }
} catch (Exception e) {
    e.printStackTrace();
    model.addAttribute("result", "Error occurred");
}
    return "create-account";
}
   @PostMapping("/authupdate")
   private void authUpdate(@RequestParam("user-id") String id ,Model model){
     try{

        String createUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
        String cmd = "update";
       String uuid=id;
   
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.set("Authorization", "Bearer " + bearerToken);
       
              HttpEntity<String> requestEntity1 = new HttpEntity<>(headers);
   
       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<String> response = restTemplate.exchange(createUrl+"?cmd="+cmd+"&uuid="+uuid, HttpMethod.POST, requestEntity1, String.class);
       System.out.println("token is "+ bearerToken);
       // model.addAttribute("token", bearerToken);
       System.out.println(response.getStatusCode());
       if (response.getStatusCode() == HttpStatus.OK) {
           model.addAttribute("token", bearerToken);
           model.addAttribute("result", "Successfully Created");
       } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
           model.addAttribute("result", "Error Not deleted");
       } else if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
           model.addAttribute("result", "UUID not Found");
       }
   
   } catch (Exception e) {
       e.printStackTrace();
       model.addAttribute("result", "Error occurred");
   }
   }
// ...
}
    
    

