package db

import model.Branch
import slick.driver.MySQLDriver.api._

/**
  * Created by rdsel on 21/10/2016.
  */
class BranchesTable(tag: Tag) extends Table[Branch](tag, "BRANCHES") {

  def id = column[Int]("BRANCH_ID", O.PrimaryKey, O.AutoInc)
  def branchName = column[String]("BRANCH_NAME")

  def * = (id, branchName)  <> (Branch.tupled, Branch.unapply)
}

object Branches extends DAO {

  def insert = ???

  def update = ???

  def delete = ???

}
