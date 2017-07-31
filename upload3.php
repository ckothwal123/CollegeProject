<?php
session_start();				
if (isset($_SESSION['user_id']))
{
?>
<html>
<head>
<title> Prime And Wordcount Uploading</title>
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
      content: '»';
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
<body><hr>
<marquee><pre><h1>Welcome to CWC             Welcome to CWC             Welcome to CWC             Welcome to CWC             Welcome to CWC</h1></pre></marquee><hr>

<a href="welcome.php"> 
<button class="button" style="vertical-align:middle"><span>Go Back</span></button></a>


<form action = "" method = "POST" enctype = "multipart/form-data">
<label>Browse File:<p style="font-size:14px">(Upload only jpg files)</p></label><input type="file" value="" name="ufile" id="ufile">
<input type="submit" value="Submit" name="submit" id="submit" /> <div>
</form>
</body>
</html>
<?php
if(isset($_POST['submit']))
{
  if($_FILES['ufile']['name']=='image.jpg'){
  $file_name = $_FILES['ufile']['name'];
  $target_path= 'upload/'.$file_name;
  move_uploaded_file($_FILES["ufile"]["tmp_name"], $target_path);
  echo "<script type='text/javascript'>alert('Uploaded Succesfully')</script>";
  }
  else{
    echo "<script type='text/javascript'>alert('Invalid format: Correct Format is image.jpg')</script>";
  }
}
?>

<?php } // End of Session check

else
{
echo "<script>
window.location.href='index.php';
</script>";
}
 ?>