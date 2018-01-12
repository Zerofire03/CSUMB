<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ReadPurchaseItemItemView PHP Page</title>
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
    	$SQL = "SELECT pi.InvoiceNumber, pi.ItemNumber, i.ArtistLastName, i.ArtistFirstName, i.ItemDescription, i.Cost, pi.RetailPrice ".
		       "FROM purchase_item pi, item i WHERE  pi.ItemNumber = i.itemNumber ORDER BY  1,2";
 
    	// Execute SQL statement
 		$res = $conn->query($SQL);
    
		if (!$res) 	{
	       	exit ("Select failed: (" . $conn->errno . ") " . $conn->error);
	    }
	?>
	    <!--  Page Headers -->
	    <h1>
	   		The James River Jewelry Purchase_Item_Item_View
	   		<br />
	   		(Appendix E: PurchaseItemItemView)
		</h1>
	    <hr />
	    <h2>
	        PurchaseItemItemView
		</h2>
	<?php	  
		// Table headers
	    echo "<table class='output' border='1'>
	    		<tr>
	    			<th>InvoiceNumber</th>
	    			<th>ItemNumber</th>
	    			<th>ArtistLastName</th>
	    			<th>ArtistFirstName</th>
                                <th>ItemDescription</th>
                                <th>Cost</th>
	    			<th>RetailPrice</th>
	    		</tr>";
	    
	    // Table data
	    while($RecordSetRow = $res->fetch_assoc() )  {
	    	echo "<tr>";
	    	echo "<td>" . $RecordSetRow['InvoiceNumber'] . "</td>";
	    	echo "<td>" . $RecordSetRow['ItemNumber'] . "</td>";
	    	echo "<td>" . $RecordSetRow['ArtistLastName'] . "</td>";
                echo "<td>" . $RecordSetRow['ArtistFirstName'] . "</td>";
	    	echo "<td>" . $RecordSetRow['ItemDescription'] . "</td>";
                echo "<td>" . $RecordSetRow['Cost'] . "</td>";
	    	echo "<td>" . $RecordSetRow['RetailPrice'] . "</td>";
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