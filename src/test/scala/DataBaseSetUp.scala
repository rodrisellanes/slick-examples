import db._
import org.scalatest.{BeforeAndAfterAll, Suite}
import slick.driver.MySQLDriver.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Database initialization trait for all integration tests
  */
trait DataBaseSetUp extends BeforeAndAfterAll { self: Suite =>

  val databaseSessionTimeout = 2 seconds

  // Session
  val db = Database.forConfig("mysqlTestdb")

  // Database tables should go here
  val categoryTable = Categories.Categories
  val branchesTable = Branches.Branches
  val usersTable = Users.Users
  val itemsTable = Items.Items

  val tables = List(categoryTable, branchesTable, usersTable, itemsTable)

  def clearAllTables: Unit = {
    for (table <- tables) {
      val deleteAllTablesQuery = DBIO.seq(table.delete)
      val resultFut = db.run(deleteAllTablesQuery)
      Await.result(resultFut, databaseSessionTimeout)
    }
  }

  def init = Try(clearAllTables).recover{
    case ex =>
      ex.printStackTrace()
      throw ex
  }

  // When this suite has finished its execution, close and release this dbs connection
  override def afterAll = { db.close() }

  // Trigger init
  init
}
