package db

import model.Item
import slick.driver.MySQLDriver.api._

/**
  * Created by rdsel on 21/10/2016.
  */
class ItemsTable(tag: Tag) extends Table[Item](tag, "ITEMS") {

  def id = column[Long]("ITEM_ID", O.PrimaryKey, O.AutoInc)
  def date = column[String]("DATE")
  def name = column[String]("ITEM")
  def price = column[Double]("PRICE")
  def category = column[Long]("CATEGORY")
  def userAssigned = column[Long]("USER_ASSIGNED")

  def * = (id, date, name, price, category, userAssigned) <> (Item.tupled, Item.unapply)

  // ForeignKeys (categories and userAssigned)
  def categoryFK = foreignKey("CATE_FK", category, TableQuery[CategoriesTable])(
    _.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)

  def userAssignedFK = foreignKey("USER_FK", userAssigned, TableQuery[UsersTable])(
    _.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)

}

object Items extends DAO {

  def insert(item: Item) = {
    val insertion = Items returning Items.map(_.id) += item
    db.run(insertion)
  }

  def update(id: Long, item: Item) =
    db.run(Items.filter(_.id === id).update(item))

  def delete(id: Long) =
    db.run(Items.filter(_.id === id).delete)

  def findById(id: Long) =
    db.run(Items.filter(_.id === id).result.headOption)

  def selectAll =
    db.run(Items.result)

    //  val innerJoin = for {
    //    (c, s) <- Users join Branches on (_.branch === _.id)
    //  } yield (c.fullName, s.branchName)
    //
    //  val a = innerJoin.result
    //  val f: Future[Seq[(String, String)]] = db.run(a)
    //  val result = Await.ready(f, Duration.Inf).value.get
    //
    //  result match {
    //    case Success(s) => s.toList foreach(x => println(x._1 + " -> " + x._2))
    //    case Failure(f) => println(s"An error has occurred: $f")
    //  }


}
