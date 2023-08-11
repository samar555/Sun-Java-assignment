<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>create-account</title>
</head>
<body>
    
    <div class="container">
        <h2 class="text-center mt-">Login</h2>
        <form action="/createCustomer?user-id=${user.uuid}" method="post">
            <input type="text" name="token" value="${token}">
            <div class="form-group">
              <input type="hidden" name="uuid" value="${user.uuid}">
              <label for="exampleInputEmail1">first Name</label>
              <input type="text" value="${user.first_name}" name="first_name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="User Name">
              <small id="emailHelp" class="form-text text-muted">We'll never share your cradential with anyone else.</small>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Last name</label>
              <input type="text"value="${user.last_name}" name ="last_name" class="form-control" id="exampleInputPassword1" placeholder="Password">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">street</label>
                <input type="text" value="${user.street}" name ="street" class="form-control" id="exampleInputPassword1" placeholder="Password">
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">address</label>
                <input type="text" value="${user.address}"name ="address" class="form-control" id="exampleInputPassword1" placeholder="Password">
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">city</label>
                <input type="text" value="${user.city}" name ="city" class="form-control" id="exampleInputPassword1" placeholder="Password">
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">email</label>
                <input type="email" value="${user.email}"name ="email" class="form-control" id="exampleInputPassword1" placeholder="Password">
              </div>
              
              <div class="form-group">
                <label for="exampleInputPassword1">phone</label>
                <input type="text" name ="phone" value="${user.phone}" class="form-control" id="exampleInputPassword1" placeholder="Password">
              </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
    
    </div>
        
       
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>