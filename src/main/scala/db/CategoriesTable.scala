package db

import slick.driver.MySQLDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by rdsel on 21/10/2016.
  */
class CategoriesTable(tag: Tag)
  extends Table[(Int, String)](tag, "CATEGORIES") {

  def id: Rep[Int] = column[Int]("CATEG_ID", O.PrimaryKey, O.AutoInc)

  def category: Rep[String] = column[String]("CATEG_NAME")

  def * : ProvenShape[(Int, String)] =
    (id, category)

}

object Categories extends DAO {


}
