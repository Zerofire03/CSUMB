<html>
   <body>
      
      <?php
         $a = 1;
         $b = 2;
		 $msg = "At the end of the loop ";
         
         for( $i = 0; $i<5; $i++ ) {
            $a += 10;
            $b += 5;
         }
         
         echo $msg . " a = $a and b = $b" ;
      ?>
   
   </body>
</html>
