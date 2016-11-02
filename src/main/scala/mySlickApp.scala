package db

import model.Item
import slick.driver.MySQLDriver.api._
import scala.concurrent.{Await, Future}
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
  if(false) createTablesIfNotExist
  // Insert Standard Data (Categories, Users & Branches)
  if(false) testDataSet


  println("Ingrese el Articulo nuevo")
  val itemName = readLine("Nombre articulo: ")
  print("Precio de compra: ")
  val itemPrice = readInt()
  print("Categoria: ")
  val itemCategory = readInt()
  print("Usuario asignado: ")
  val itemUsrAssigned = readInt()

  Items.insert(Item(0, 2016, itemName, itemPrice, itemCategory, itemUsrAssigned))
  Items.delete(1)


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

  // Close DB Configuration
  db.close
}
