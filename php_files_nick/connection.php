<?php
$db_name= "cse442_542_2020_spring_teamk_db";
$mysql_username = "njceccar";
$mysql_password = "50234356";
$server_name = "tethys.cse.buffalo.edu:3306";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password);
if($conn){
	echo "connection success";
}else{
	echo "connection_failed";
}
?>