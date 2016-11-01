package db

import slick.driver.MySQLDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by rdsel on 21/10/2016.
  */
class ItemsTable(tag: Tag)
  extends Table[(Long, Int, String, Double, Int, Int)](tag, "ITEMS") {

  def id: Rep[Long] = column[Long]("ITEM_ID", O.PrimaryKey, O.AutoInc)
  def date: Rep[Int] = column[Int]("DATE")
  def name: Rep[String] = column[String]("ITEM")
  def price: Rep[Double] = column[Double]("PRICE")
  def category: Rep[Int] = column[Int]("CATEGORY")
  def userAssigned: Rep[Int] = column[Int]("USER_ASSIGNED")

  def * : ProvenShape[(Long, Int, String, Double, Int, Int)] =
    (id, date, name, price, category, userAssigned)

  // ForeignKeys (categories and userAssigned)
  def categoryFK = foreignKey("CATE_FK", category, TableQuery[CategoriesTable])(
    _.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)

  def userAssignedFK = foreignKey("USER_FK", userAssigned, TableQuery[UsersTable])(
    _.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)


}
