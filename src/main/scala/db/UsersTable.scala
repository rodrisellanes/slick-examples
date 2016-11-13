package db

import model.User
import slick.driver.MySQLDriver.api._
import scala.concurrent.Future

/**
  * Created by rdsel on 21/10/2016.
  */
class UsersTable(tag: Tag) extends Table[User](tag, "USERS") {

  def id = column[Long]("USER_ID", O.PrimaryKey, O.AutoInc)
  def fullName = column[String]("FULL_NAME")
  def branch = column[Long]("BRANCH")

  def * = (id, fullName, branch) <> (User.tupled, User.unapply)

  // ForeignKey
  def branchFK = foreignKey("BRANCH_FK", branch, TableQuery[BranchesTable])(
    _.id, onUpdate = ForeignKeyAction.Cascade, onDelete = ForeignKeyAction.Cascade)
}

object Users extends DAO {

  def insert(user: User): Future[Long] = {
    val insertion = Users returning Users.map(_.id) += user
    db.run(insertion)
  }

  def update(id: Long, user: User) =
    db.run(Users.filter(_.id === id).update(user))

  def delete(id: Long) =
    db.run(Users.filter(_.id === id).delete)

  def findById(id: Long): Future[Option[User]] =
    db.run(Users.filter(_.id === id).result.headOption)

  def selectAll =
    db.run(Users.result)

}
