<?php
require 'DBConnector.php';

class Exercises{

	const TABLE_NAME = "exercises";

	public static function getAllExercises(){

		try{

			$query = 'SELECT * FROM '.self::TABLE_NAME;

			$my_sql_command = DBConnector::getInstance()->getConnection()->prepare($query);

			$my_sql_command->execute();

			return $my_sql_command->fetchAll(PDO::FETCH_ASSOC);

		}catch( PDOException $exception){

			return 'error: '.$exception;

		}


	}


}

?>