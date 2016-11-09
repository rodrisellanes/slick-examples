package db

import model.Item
import slick.driver.MySQLDriver.api._
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


/**
  * Created by rdsel on 21/10/2016.
  */
class ItemsTable(tag: Tag) extends Table[Item](tag, "ITEMS") {

  def id = column[Long]("ITEM_ID", O.PrimaryKey, O.AutoInc)
  def date = column[String]("DATE")
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
    createTablesIfNotExist
    val insertion = DBIO.seq(Items += item)
    db.run(insertion)
  }

  def updateById(id: Long, item: Item) =
    db.run(Items.filter(_.id === id).update(item))

  def deleteById(id: Long) = {
    db.run(Items.filter(_.id === id).delete)
  }

  def selectById(id: Long) = {
    val query = Items.filter(_.id === id)
    db.run(query.result)
  }

  def selectAll = {
    val query = Items
    db.run(query.result)

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


}
