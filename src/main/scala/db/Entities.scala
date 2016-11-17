package db


trait Entities {

  val db = DBProperties.dbConnection

  def insert[T](aObject: T) = {
    val insertion = ???
    db.run(insertion)
  }

  def update[T](id: Long, aObject: T) = {
    val objToUpdate = ???
    db.run(objToUpdate)
  }

  def delete(id: Long) = {
    val objToDelete = ???
    db.run(objToDelete)
  }

  def findById(id: Long) = {
    val objToFind = ???
    db.run(objToFind)
  }

  def selectAll = {
    val allObjs = ???
    db.run(allObjs)
  }

}
