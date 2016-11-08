import db.Items
import model.Item

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn._

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
  Items.insert(Item(0, 2016, itemName, itemPrice, itemCategory, itemUsrAssigned))

  // Delete item
  Await.ready(Items.deleteById(4), Duration.Inf).value.get

  // Update item
  val item1 = Item(0, 2015, "Mouse Genius", 90, 1, 4)
  Await.ready(Items.updateById(5, item1), Duration.Inf).value.get


}
