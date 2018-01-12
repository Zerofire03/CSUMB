<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ReadItem PHP Page</title>
		<style type="text/css">
			h1 {text-align: center; color: blue}
			h2 {font-family: Ariel, sans-serif; text-align: left; color: blue}
			p.footer {text-align: center}
			table.output {font-family: Ariel, sans-serif}
		</style>
	</head>
	<body>
	<?php
		// make a connection
		$Host = "192.168.0.44";
		$Database = "jrj";
    	$User = "root";
    	$Password = "Becky1991@";
		$Port = 32793;
		
		$conn = new mysqli($Host, $User, $Password , $Database, $Port);
		if ($conn->connect_errno) {
			exit ("Failed to connect: (" . $conn->connect_errno . ") " . $conn->connect_error );
		}
		
		// Create SQL statement
    	$SQL = "SELECT * FROM ITEM";
 
    	// Execute SQL statement
 		$res = $conn->query($SQL);
    
		if (!$res) 	{
	       	exit ("Select failed: (" . $conn->errno . ") " . $conn->error);
	    }
    
	?>
	    <!--  Page Headers -->
	    <h1>
	   		The James River Jewelry ITEM Table
		</h1>
	    <hr />
	    <h2>
	        ITEM
		</h2>
	<?php	  
		// Table headers
	    echo "<table class='output' border='1'>
	    		<tr>
	    			<th>ItemNumber</th>
	    			<th>ItemDescription</th>
	    			<th>Cost</th>
	    			<th>ArtistLastName</th>
                                <th>ArtistFirstName</th>
	    		</tr>";
	    
	    // Table data
		while($RecordSetRow = $res->fetch_assoc() )  {
	    	echo "<tr>";
	    	echo "<td>" . $RecordSetRow['ItemNumber'] . "</td>";
	    	echo "<td>" . $RecordSetRow['ItemDescription'] . "</td>";
	    	echo "<td>" . $RecordSetRow['Cost'] . "</td>";
	    	echo "<td>" . $RecordSetRow['ArtistLastName'] . "</td>";
	    	echo "<td>" . $RecordSetRow['ArtistLastName'] . "</td>";
            echo "</tr>";
		}
	    echo "</table>";
	    
	    // Close connection
	    $conn->close();
	?>
		<br />
		<hr />
		<p class="footer">
			<a href="index.html">Return to James River Jewelry Home Page</a>
		</p>
		<hr />
    </body>
</html>