package db

import controller.Item
import slick.driver.MySQLDriver.api._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}
import scala.io.StdIn._

/**
  * Created by rdsel on 15/10/2016.
  */
object mySlickApp extends App with DAO {

  //DataBase get configuration
  val db = Database.forConfig("mysqldb")

  def createTablesIfNotExist = {
    val schema = DBIO.seq((items.schema ++ categories.schema ++ branches.schema ++ users.schema).create)
    db.run(schema)
  }
  // Create DB Schema
  createTablesIfNotExist
  // Insert Standard Data (Categories, Users & Branches)
//  insertStandardData

//    db.run(items.filter(_.price < 100).delete)
  //  db.run(DBIO.seq((items.schema ++ categories.schema ++ branches.schema ++ users.schema).drop))

  println("Ingrese el Articulo nuevo")
  val itemName = readLine("Nombre articulo: ")
  print("Precio de compra: ")
  val itemPrice = readInt()
  print("Categoria: ")
  val itemCategory = readInt()
  print("Usuario asignado: ")
  val itemUsrAssigned = readInt()

  val it = new Item(itemName, itemPrice, itemCategory, itemUsrAssigned)

  println(it.toString)

  val innerJoin = for {
    (c, s) <- users join branches on (_.branch === _.id)
  } yield (c.fullName, s.branchName)

  val a = innerJoin.result
  val f: Future[Seq[(String, String)]] = db.run(a)
  val result = Await.ready(f, Duration.Inf).value.get

  result match {
    case Success(s) => s.toList foreach(x => println(x._1 + " -> " + x._2))
    case Failure(f) => println(s"An error has occurred: $f")
  }



//
//  // Materialized
//  val q = for { c <- categories } yield c.*
//  val a1 = q.result
//  val f1: Future[Seq[(Int, String)]] = db.run(a1)
//
//  f1.onComplete { s => println(s"Result: $s") }

  // Close DB Configuration
  db.close
}
