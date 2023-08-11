<%@page import ="com.java.assignment.assignment.helper.customerDataWithuuid" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" 
  integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
   crossorigin="anonymous">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Dashboared</title>
</head>
<body>
    <div class="container">
        <h3 class="text-center mt-5">Dashboared</h3>
        <!-- <input type="text" name="token" value=${token}> -->
        <!-- <p>${token}</p> -->
        <table class="table">
        <h1 ${result} class="text-center"></h1>
            <thead class="thead-dark">
              <tr>
                <th scope="col">ID</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Address</th>
                <th scope="col">City</th>
                <th scope="col">State</th>
                <th scope="col">email</th>
                <th scope="col">Phone</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              <% 
              Object customerArrayObj = request.getAttribute("customerList");
              if (customerArrayObj instanceof java.util.List) {
                java.util.List<customerDataWithuuid> customerList = (java.util.List<customerDataWithuuid>) customerArrayObj;
                  for (customerDataWithuuid p : customerList) {
          %>
          <input type="hidden" name="user-id" value="<%= p.getUuid() %>">
                <tr>
                  <th scope="row" >#</th>
                  <td><%= p.getFirst_name() %></td>
                  <td><%= p.getLast_name() %></td>
                  <td><%= p.getStreet() %></td>
                  <td><%= p.getAddress()%></td>
                  <td><%= p.getCity()%></td>
                  <td><%= p.getEmail()%></td>
                  <td><%= p.getPhone() %></td>
                  
                  <td><a href="user-delete?user-id=<%= p.getUuid() %>"class="fas fa-trash" style="color: red;"></a>
                  </td>
                  <td><a href="user-update?user-id=<%= p.getUuid() %>" class="fas fa-pen"></a>
                  </td>
                </tr>
                <%
              }
          }
      %>
              
              
             
            </tbody>
          </table>
          <a href="create-account" class="btn btn-primary">create-account</a>
    </div>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
      
</body>
</html>