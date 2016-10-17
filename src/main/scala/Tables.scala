import slick.driver.MySQLDriver.api._
import slick.lifted.{ProvenShape, ForeignKeyQuery}

class Items(tag: Tag)
  extends Table[(Int, String, Double, String, String)](tag, "ITEMS") {

  def id: Rep[Int] = column[Int]("ITEM_ID", O.PrimaryKey)
  def name: Rep[String] = column[String]("ITEM")
  def price: Rep[Double] = column[Double]("PRICE")
  def category: Rep[String] = column[String]("CATEGORY")
  def userAssigned: Rep[String] = column[String]("USER_ASSIGNED")

  def * : ProvenShape[(Int, String, Double,String, String)] =
    (id, name, price, category, userAssigned)
}

