package db

import slick.driver.MySQLDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by rdsel on 21/10/2016.
  */
class UsersTable(tag: Tag)
  extends Table[(Int, String, Int)](tag, "USERS") {

  def id: Rep[Int] = column[Int]("USER_ID", O.PrimaryKey, O.AutoInc)

  def fullName: Rep[String] = column[String]("FULL_NAME")

  def branch: Rep[Int] = column[Int]("BRANCH")

  def * : ProvenShape[(Int, String, Int)] =
    (id, fullName, branch)

  // ForeignKey
  def branchFK = foreignKey("BRANCH_FK", branch, TableQuery[BranchesTable])(
    _.id, onUpdate = ForeignKeyAction.Cascade, onDelete = ForeignKeyAction.Cascade)
}

object Users extends DAO {


}
