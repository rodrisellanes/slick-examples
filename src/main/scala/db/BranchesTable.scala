package db

import slick.driver.MySQLDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by rdsel on 21/10/2016.
  */
class BranchesTable(tag: Tag)
  extends Table[(Int, String)](tag, "BRANCHES") {

  def id: Rep[Int] = column[Int]("BRANCH_ID", O.PrimaryKey, O.AutoInc)
  def branchName: Rep[String] = column[String]("BRANCH_NAME")

  def * : ProvenShape[(Int, String)] =
    (id, branchName)
}
