<?php
session_start();				
if (isset($_SESSION['user_id']))
{
?>
    <!DOCTYPE HTML>
    <html lang="en">
    <head>
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

    <meta charset="utf-8">
    <title>CWC</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel='stylesheet' id='prettyphoto-css'  href="css/prettyPhoto.css" type='text/css' media='all'>
    <link href="css/fontello.css" type="text/css" rel="stylesheet">
    <!--[if lt IE 7]>
            <link href="css/fontello-ie7.css" type="text/css" rel="stylesheet">  
        <![endif]-->
    <!-- Google Web fonts -->
    <link href='http://fonts.googleapis.com/css?family=Quattrocento:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Noticia+Text' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <style>

    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    <!-- Favicon -->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- JQuery -->
    <script type="text/javascript" src="js/jquery.js"></script>
    <!-- Load ScrollTo -->
    <script type="text/javascript" src="js/jquery.scrollTo-1.4.2-min.js"></script>
    <!-- Load LocalScroll -->
    <script type="text/javascript" src="js/jquery.localscroll-1.2.7-min.js"></script>
    <!-- prettyPhoto Initialization -->
    <script type="text/javascript" charset="utf-8">
          $(document).ready(function(){
            $("a[rel^='prettyPhoto']").prettyPhoto();
          });
        </script>
    </head>
    <body>
     <div id="headerwrap">
      <header class="clearfix"><hr/>
        <marquee style:"scrolldellay=0.006; scrollamount=15;"><pre><h1>Welcome to CWC             Welcome to CWC             Welcome to CWC             Welcome to CWC             Welcome to CWC</h1></pre></marquee><hr/>
        <div class="container">
         <div class="row">
            <div class="span12">
            </div></div>
 <div><a href="results/image.jpg">
 <button class="button" style="vertical-align:middle"><span> Original Image </span></button></a></div>

 <div><a href="results/blur.jpg">
 <button class="button" style="vertical-align:middle"><span> Blurred Image </span></button></a></a></div>

 <div><a href="results/wordresult.php">
 <button class="button" style="vertical-align:middle"><span> Word Count Result </span></button></a></div>

 <div><a href="results/primeresult.php">
 <button class="button" style="vertical-align:middle"><span> Prime Number Count Result </span></button></a></div>
       
 <div><a href="welcome.php">
 <button class="button" style="vertical-align:middle"><span> Go Back </span></button></a></div>
         
            </div>
          </div>

    <!-- Loading the javaScript at the end of the page -->
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
    <script type="text/javascript" src="js/site.js"></script>
    
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
