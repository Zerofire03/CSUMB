<html>
   <body>
   
      <?php
         $d = date("D");
         
         if ($d == "Sun")
            echo "Have a nice Sunday!"; 
         
         else
            echo "Have a nice $d."; 
            echo date("r");
      ?>
   
   </body>
</html>