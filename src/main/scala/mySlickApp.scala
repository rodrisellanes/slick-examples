import db.Items
import model.Item

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn._
import scala.util.{Failure, Success}

/**
  * Created by rdsel on 15/10/2016.
  */

object mySlickApp extends App {

  println("Ingrese el Articulo nuevo")
  val itemName = readLine("Nombre articulo: ")
  print("Precio de compra: ")
  val itemPrice = readInt()
  print("Categoria: ")
  val itemCategory = readInt()
  print("Usuario asignado: ")
  val itemUsrAssigned = readInt()



  // Create a new item
//  val newItem = Items.insert(Item(0, "8/11/2016", itemName, itemPrice, itemCategory, itemUsrAssigned))
//  Await.ready(newItem, Duration.Inf)

  // Delete item
//  Await.ready(Items.deleteById(0), Duration.Inf).value.get

  // Update item
//  val item1 = Item (5, "11/10/2015", "Mouse Genius", 90, 1, 4)
//  Await.ready(Items.updateById(0, item1), Duration.Inf).value.get

  // Report item by ID
//  val result = Await.ready(Items.selectById(6), Duration.Inf).value.get
//  println("result: " + result)

  // Report all items
  val allResults = Await.ready(Items.selectAll, Duration.Inf).value.get

  allResults match {
    case Success(s) => s foreach(println(_))
    case Failure(f) => new Throwable("An error has occurred")
  }

}
