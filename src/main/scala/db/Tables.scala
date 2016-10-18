package db

import slick.driver.MySQLDriver.api._
import slick.lifted.ProvenShape

class Items(tag: Tag)
  extends Table[(Int, Int, String, Double, Int, Int)](tag, "ITEMS") {

  def id: Rep[Int] = column[Int]("ITEM_ID", O.PrimaryKey)
  def date: Rep[Int] = column[Int]("DATE")
  def name: Rep[String] = column[String]("ITEM")
  def price: Rep[Double] = column[Double]("PRICE")
  def category: Rep[Int] = column[Int]("CATEGORY")
  def userAssigned: Rep[Int] = column[Int]("USER_ASSIGNED")

  def * : ProvenShape[(Int, Int, String, Double, Int, Int)] =
    (id, date, name, price, category, userAssigned)
}

class Users(tag: Tag)
  extends Table[(Int, String, Int)](tag, "USERS") {

  def id: Rep[Int] = column[Int]("USER_ID", O.PrimaryKey)
  def fullName: Rep[String] = column[String]("FULL_NAME")
  def branch: Rep[Int] = column[Int]("BRANCH")

  def * : ProvenShape[(Int, String, Int)] =
    (id, fullName, branch)
}

class Branches(tag: Tag)
  extends Table[(Int, String)](tag, "BRANCHES") {

  def id: Rep[Int] = column[Int]("BRANCH_ID", O.PrimaryKey)
  def branchName: Rep[String] = column[String]("BRANCH_NAME")

  def * : ProvenShape[(Int, String)] =
    (id, branchName)
}

class Categories(tag: Tag)
  extends Table[(Int, String)](tag, "CATEGORIES") {

  def id: Rep[Int] = column[Int]("CATEG_ID", O.PrimaryKey)
  def category: Rep[String] = column[String]("CATEG_NAME")

  def * : ProvenShape[(Int, String)] =
    (id, category)
}

//class Budget(tag: Tag)
//  extends Table[(Int, String, Int,)](tag, "BUDGET") {
//}


