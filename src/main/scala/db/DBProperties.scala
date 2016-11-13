package db

import slick.driver.MySQLDriver.api._
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.util.Try

/**
  * Created by rdsel on 13/11/2016.
  */
object DBProperties {

  // Database connection & configuration
  lazy val dbConnection = Database.forConfig("mysqldb")

  // Database tables should go here
  private val categoryTable = Categories.Categories
  private val branchesTable = Branches.Branches
  private val usersTable = Users.Users
  private val itemsTable = Items.Items

  private val schemas = itemsTable.schema ++ categoryTable.schema ++  usersTable.schema ++ branchesTable.schema

  private def createAllTables =
    dbConnection.run(DBIO.seq(schemas.create))

  private def dropAllTables =
    dbConnection.run(DBIO.seq(schemas.drop))

  def clearAllTables: Unit = {
    val tables = List(itemsTable, categoryTable, usersTable, branchesTable)
    for (table <- tables) {
      val deleteAllTablesQuery = DBIO.seq(table.delete)
      val resultFut = dbConnection.run(deleteAllTablesQuery)
      Await.result(resultFut, 2 seconds)
    }
  }

  def init = {
    println("Connection to the DB")
    Try(dbConnection).recover{
      case ex =>
        ex.printStackTrace()
        throw ex
    }

    println("Creating Tables in case doesn't exist")
    Await.result(createAllTables, 2 seconds)

    clearAllTables
    println("Drop all schema form the DB")
    Await.result(dropAllTables, 2 seconds)
  }

}
