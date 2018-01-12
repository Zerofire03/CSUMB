<html>
   <head>
      <title>Php sql example </title>      
   </head>
   
   <body>
		<?php
			// make a connection
			$Host = "localhost";
			$Database = "jrj";
			$User = "root";
			$Password = "bike4u";
			$Port = 3306;
		
			$conn = new mysqli($Host, $User, $Password , $Database, $Port);
			if ($conn->connect_errno) {
				exit ("Failed to connect: (" . $conn->connect_errno . ") " . $conn->connect_error );
			}
			
			$SQL = "select * from pet";
			// Execute SQL statement
			$res = $conn->query($SQL);
    
			if (!$res) 	{
	        	exit ("Select failed: (" . $conn->errno . ") " . $conn->error);
	    	}
			
			// Table headers
			echo "<table class='output' border='1'>
	    		<tr>
	    			<th>PetID</th>
	    			<th>PetName</th>
	    			<th>PetType</th>
	    			<th>PetBreed</th>
					<th>PetDOB</th>
					<th>OwnerID</th>
	    		</tr>";
	    
			// Table data
			while($RecordSetRow = $res->fetch_assoc() )  {
				echo "<tr>";
				echo "<td>" . $RecordSetRow['PetID'] . "</td>";
				echo "<td>" . $RecordSetRow['PetName'] . "</td>";
				echo "<td>" . $RecordSetRow['PetType'] . "</td>";
				echo "<td>" . $RecordSetRow['PetBreed'] . "</td>";
				echo "<td>" . $RecordSetRow['PetDOB'] . "</td>";
				echo "<td>" . $RecordSetRow['OwnerID'] . "</td>";
				echo "</tr>";
			}
			echo "</table>";
			
			// commit and close connection
			$conn->commit();
			$conn->close();
			
			echo "<br>Goodbye! <br>";
		?>
   </body>
</html>