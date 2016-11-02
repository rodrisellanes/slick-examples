package db

import model.Item
import mySlickApp.db
import slick.driver.MySQLDriver.api._


/**
  * Created by rdsel on 21/10/2016.
  */
class ItemsTable(tag: Tag) extends Table[Item](tag, "ITEMS") {

  def id = column[Long]("ITEM_ID", O.PrimaryKey, O.AutoInc)
  def date = column[Int]("DATE")
  def name = column[String]("ITEM")
  def price = column[Double]("PRICE")
  def category = column[Int]("CATEGORY")
  def userAssigned = column[Int]("USER_ASSIGNED")

  def * = (id, date, name, price, category, userAssigned) <> (Item.tupled, Item.unapply)

  // ForeignKeys (categories and userAssigned)
  def categoryFK = foreignKey("CATE_FK", category, TableQuery[CategoriesTable])(
    _.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)

  def userAssignedFK = foreignKey("USER_FK", userAssigned, TableQuery[UsersTable])(
    _.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)

}

object Items extends DAO {

  def insert(item: Item) = {
    val insertion = DBIO.seq(items += item)
    db.run(insertion)
  }

  def update(item: Item) = ???

  def delete(id: Int) = {
    db.run(items.filter(_.id === id).delete)
  }
  //    db.run(items.filter(_.price < 100).delete)

  def selectAll() = ???

  def selectById(id: Int): Option[Item]  = {
    items.filter(_.id === id).take(1).result.map(_.headOption)
  }


}
