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

  def insert(branch: Branch) = {
    createTablesIfNotExist
    val insertion = DBIO.seq(Branches += branch)
    db.run(insertion)
  }

  def update(id: Long, branch: Branch) =
    db.run(Branches.filter(_.id === id).update(branch))

  def delete(id: Long) =
    db.run(Branches.filter(_.id === id).delete)

  def selectAll = {
    val query = Branches
    db.run(query.result)
  }

  def selectById(id: Long) = {
    val query = Branches.filter(_.id === id)
    db.run(query.result)
  }


}
