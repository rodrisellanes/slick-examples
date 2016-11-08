package db

import slick.lifted.TableQuery
import slick.driver.MySQLDriver.api._

/**
  * Created by Rodrigo on 18/10/2016.
  */
private[db] trait DAO {
  lazy val Items = TableQuery[ItemsTable]
  lazy val Branches = TableQuery[BranchesTable]
  lazy val Categories = TableQuery[CategoriesTable]
  lazy val Users = TableQuery[UsersTable]


  val db = Database.forConfig("mysqldb")

  def createTablesIfNotExist = {
    val schemas = DBIO.seq((Items.schema ++ Categories.schema ++ Branches.schema ++ Users.schema).create)
    db.run(schemas)
  }
}