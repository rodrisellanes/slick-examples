package db

import slick.lifted.TableQuery

/**
  * Created by Rodrigo on 18/10/2016.
  */
private[db] trait DAO {
  lazy val Items = TableQuery[ItemsTable]
  lazy val Branches = TableQuery[BranchesTable]
  lazy val Categories = TableQuery[CategoriesTable]
  lazy val Users = TableQuery[UsersTable]

  val db = DBProperties.dbConnection
}