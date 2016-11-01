package db

import slick.lifted.TableQuery

/**
  * Created by Rodrigo on 18/10/2016.
  */
trait DAO {

  lazy val items = TableQuery[ItemsTable]
  lazy val branches = TableQuery[BranchesTable]
  lazy val categories = TableQuery[CategoriesTable]
  lazy val users = TableQuery[UsersTable]

}
