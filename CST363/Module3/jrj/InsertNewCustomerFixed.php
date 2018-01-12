<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>InputInvoiceForm</title>
		<style type="text/css">
			h1 {text-align: center; color: blue}
			h2 {font-family: Ariel, sans-serif; text-align: left; color: blue}
			p.footer {text-align: center}
			table.output {font-family: Ariel, sans-serif}
		</style>
	</head>
	<body>
	<?php
		// Get connection
		$Host = "localhost";
		$Database = "jrj";
    	$User = "root";
    	$Password = "math1504U";
		$Port = 3306;
		
		$conn = new mysqli($Host, $User, $Password , $Database, $Port);
        if ($conn->connect_errno) {
              exit ("Failed to connect to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error );
        }
    
		// Create short variable names
		
		$LastName = $_POST["LastName"];
		$FirstName = $_POST["FirstName"];
		$Phone = $_POST["Phone"];
		$Email = $_POST["Email"];
        
        // sanitize string values entered by user_error
        $LastName = htmlspecialchars($LastName);
        $FistName = htmlspecialchars($FirstName);
        $Phone = htmlspecialchars($Phone);
        $Email = htmlspecialchars($Email);
		
		// Create SQL statement to INSERT new data
		$SQLINSERT = "INSERT INTO CUSTOMER (CustomerId, LastName, FirstName, Phone, Email) ";
		$SQLINSERT .= " VALUES( null, ?, ?, ?, ?)";
	
        // prepare sql statement containing parameter markers,  i.e. ?'s
        $stmt = $conn->prepare($SQLINSERT);
        
        //  $stmt value of False, means prepare failed.
        if (! $stmt) {
              exit ("Failed to prepare to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error );
        }
        
        // set the variable values.  The 's' indicates a string value
        $stmt->bind_param('ssss', $LastName, $FirstName, $Phone, $Email);
        
		// Execute SQL statement
		if (! $stmt->execute() ) {
            // error occurred
            exit ("Failed to insert to MySQL aa: (" . $stmt->errno . ") " . $stmt->error );
        }
        
        // check that 1 rows was inserted.
        $icount = $stmt->affected_rows;
        if ($icount != 1) {
            // error occurred
            exit ("Insert count should be 1. Actual count =". $icount);
        }
	
		
		echo "<h1>
				The James River Designs CUSTOMER Table
			</h1>
			<hr />";

			echo "<h2>
				New Customer Added:
			</h2>
			<table>
				<tr>";
				
				echo "<tr>";
				echo "<td>LastName:</td>";
				echo "<td>" . $LastName . "</td>";
				echo "</tr>";
				echo "<tr>";
				echo "<td>FirstName:</td>";
				echo "<td>" . $FirstName . "</td>";
				echo "</tr>";
				echo "<td>Phone:</td>";
				echo "<td>" . $Phone . "</td>";
				echo "</tr>";
				echo "<td>EmailAddress:</td>";
				echo "<td>" . $Email . "</td>";
				echo "</tr>";
			echo "</table><br /><hr />";


			
		// Create SQL statement to read CUSTOMER table data
    	$SQL = "SELECT * FROM CUSTOMER";
 
    	// Execute SQL statement
    	$res = $conn->query($SQL);
    
    	// Test existence of recordset
    	if (!$res)
	    	{
	        	exit ("Select failed: (" . $conn->errno . ") " . $conn->error);
	    	}
	?>
	    <!--  Page Headers -->
	    <h1>
	   		The James River Jewelry CUSTOMER Table
		</h1>
	    <hr />
	    <h2>
	        CUSTOMER
		</h2>
	<?php	  
		// Table headers
	    echo "<table class='output' border='1'>
	    		<tr>
	    			<th>LastName</th>
	    			<th>FirstName</th>
	    			<th>Phone</th>
	    			<th>Email</th>
	    		</tr>";
	    
	    // Table data
	    while($RecordSetRow = $res->fetch_assoc() )
	    	{
	    	echo "<tr>";
	       	echo "<td>" . $RecordSetRow['LastName'] . "</td>";
	    	echo "<td>" . $RecordSetRow['FirstName'] . "</td>";
	    	echo "<td>" . $RecordSetRow['Phone'] . "</td>";
	    	echo "<td>" . $RecordSetRow['Email'] . "</td>";
	    	}
	    echo "</table>";

	    //  Close connection
		$conn->close();
	?>
		<br />
		<hr />
		<p class="footer">	
			<a href="jrj.html">
				Return to James River Jewelry Home Page
			</a>
		</p>
		<hr />
	</body>
</html>
