package db

import model.Branch
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future

/**
  * Created by rdsel on 21/10/2016.
  */
class BranchesTable(tag: Tag) extends Table[Branch](tag, "BRANCHES") {

  def id = column[Long]("BRANCH_ID", O.PrimaryKey, O.AutoInc)
  def branchName = column[String]("BRANCH_NAME")

  def * = (id, branchName)  <> (Branch.tupled, Branch.unapply)
}

object Branches extends DAO {

  def insert(branch: Branch): Future[Long] = {
    val insertion = Branches returning Branches.map(_.id) += branch
    db.run(insertion)
  }

  def update(id: Long, branch: Branch) =
    db.run(Branches.filter(_.id === id).update(branch))

  def delete(id: Long) =
    db.run(Branches.filter(_.id === id).delete)

  def findById(id: Long) =
    db.run(Branches.filter(_.id === id).result.headOption)

  def selectAll =
    db.run(Branches.result)



}
