<?php

require_once 'DBConstants.php';

class DBConnector{

	private static $s_instance = null;
	private static $s_my_sql_pdo_instance = null;

	public static function getInstance(){

		if ( self::$s_instance === null ){

			self::$s_instance = new self();

		}

		return self::$s_instance;

	}


	final private function __construct(){

		try{

			self::getConnection();

		}catch( PDOException $exception){


		}

	}

	function _destructor(){

		self::$s_my_sql_pdo_instance = null;

	}

	final protected function __clone()
    {
    }

	public function getConnection(){

		if ( self::$s_my_sql_pdo_instance == null){

			self::$s_my_sql_pdo_instance = new PDO('mysql:host='.DATABASE_HOST_NAME.';dbname='.DATABASE_NAME, DATABASE_USER, DATABASE_PASSWORD,array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));


		   self::$s_my_sql_pdo_instance->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

		}

		return self::$s_my_sql_pdo_instance;

	}
}


?>