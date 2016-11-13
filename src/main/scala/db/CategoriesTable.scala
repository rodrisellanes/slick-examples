package db

import model.Category
import slick.driver.MySQLDriver.api._
import scala.concurrent.Future

/**
  * Created by rdsel on 21/10/2016.
  */
class CategoriesTable(tag: Tag)
  extends Table[Category](tag, "CATEGORIES") {

  def id = column[Long]("CATEG_ID", O.PrimaryKey, O.AutoInc)
  def category = column[String]("CATEG_NAME")

  def * = (id, category) <> (Category.tupled, Category.unapply)
}

object Categories extends DAO {

  def insert(category: Category): Future[Long] = {
    val insertion = Categories returning Categories.map(_.id) += category
    db.run(insertion)
  }

  def delete(id: Long) =
    db.run(Categories.filter(_.id === id).delete)

  def findById(id: Long) =
    db.run(Categories.filter(_.id === id).result.headOption)

  def selectAll =
    db.run(Categories.result)
}
