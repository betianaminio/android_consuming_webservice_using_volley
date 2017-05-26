<?php
require 'Exercises.php';

if ( $_SERVER['REQUEST_METHOD'] == 'GET'){

	$exercises = Exercises::getAllExercises();

	if ($exercises) {

        $result["exercises"] = $exercises;

        print json_encode($result);

    } else {

        print json_encode( "An error has been occurred");
    }

}


?>