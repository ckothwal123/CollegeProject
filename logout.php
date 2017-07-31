<?php
session_start();
if(session_destroy()) // Destroying All Sessions
{
echo "<script>
window.location.href='index.php';
</script>";
}
?>
