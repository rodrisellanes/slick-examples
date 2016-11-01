package controller

/**
  * Created by rdsel on 31/10/2016.
  */
class Item(name: String, price: Double, category: Int, userAssigned: Int) {

  val this.name = name
  val this.price = price
  val this.category = category
  val this.userAssigned = userAssigned

  def newItem() = ???

  override def toString: String = s"Nuevo Articulo: $name - $price - $category - $userAssigned"
}
