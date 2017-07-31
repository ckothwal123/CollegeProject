<?php
session_start();				
if (isset($_SESSION['user_id']))
{
?>


<html>
<head>
<title>Welcome Page</title>
    <style>
    .button {
      display: inline-block;
      border-radius: 4px;
      background-color: #008CBA;
      border: none;
      color: #FFFFFF;
      text-align: center;
      font-size: 17px;
      padding: 20px;
      width: 100%;
      transition: all 0.5s;
      cursor: pointer;
      margin: 5px;
    }

    .button span {
      cursor: pointer;
      display: inline-block;
      position: relative;
      transition: 0.5s;
    }

    .button span:after {
      content: '>>';
      position: absolute;
      opacity: 0;
      top: 0;
      right: -20px;
      transition: 0.5s;
    }

    .button:hover span {
      padding-right: 25px;
    }

    .button:hover span:after {
      opacity: 1;
      right: 0;
    }

    body {
    color: white;
    background-image: url("./imageis.jpg");
    background-repeat: repeat-x repeat-y;
    background-position: right top;
    font-size: 26px;
}
    </style>

</head>

<body>

<hr>
<marquee><pre><h1>Welcome to CWC             Welcome to CWC             Welcome to CWC             Welcome to CWC             Welcome to CWC</h1></pre></marquee><hr>


<a href="./upload1.php"> 
<button class="button" style="vertical-align:middle"><span> Link to Upload Prime Number File </span></button></a>



<a href="./upload2.php"> 
<button class="button" style="vertical-align:middle"><span> Link to Upload Word Count File </span></button></a>



<a href="./upload3.php"> 
<button class="button" style="vertical-align:middle"><span> Link to Upload Image File </span></button></a>



<a href="./Results.php"> 
<button class="button" style="vertical-align:middle"><span> Go to results </span></button></a>
<a href="logout.php"> 
<button class="button" style="vertical-align:middle"><span> Logout</span></button></a>

</body>
</html>

<?php } // End of Session check

else
{
echo "<script>
window.location.href='index.php';
</script>";
}
 ?>
 

