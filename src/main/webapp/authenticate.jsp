<%@ page import="java.io.*, java.net.*" %>
<%@ page import="org.json.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
   <p> 

    <!-- <%@ page import="org.json.JSONObject" %> -->


<%
try{
    // Step 1: Retrieve user input from the form
    String loginId = request.getParameter("login_id");
    String password = request.getParameter("password");
    
    // Step 2: Authenticate and get Bearer token
    String authUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
    
    String bearerToken = "";
    try {
        URL url = new URL(authUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
    
        String jsonInputString = "{\"login_id\": \"" + loginId + "\", \"password\": \"" + password + "\"}";
    
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response1 = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response1.append(responseLine.trim());
            }
    
            // Parse authentication response to get Bearer token
            JSONObject jsonResponse = new JSONObject(response1.toString());
            bearerToken = jsonResponse.getString("access_token");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    if (!bearerToken.isEmpty()) {
        // Redirect to the customer creation page
        response.sendRedirect("dashboared.jsp?token=" + bearerToken);
    } else {
        // Handle authentication failure, redirect back to login page
        response.sendRedirect("log-in.jsp");
    }
    
}catch(Exception e){
System.out.println(e);
}
%>
</p>
</body>
</html>