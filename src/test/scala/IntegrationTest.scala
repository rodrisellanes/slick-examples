import db._

import model.{Branch, Category, Item, User}
import org.scalatest.FunSuite
import scala.concurrent.Await

/**
  * Created by rdsel on 8/11/2016.
  */
class CategoryIntegrationTest extends FunSuite with DataBaseSetUp {

  test("Inserting a category in the database") {
    val categoryToInsert = Category(0, "Hardware")
    val insertFut = Categories.insert(categoryToInsert)
    val resultId = Await.result(insertFut, databaseSessionTimeout)

    val categoryCopy = categoryToInsert.copy(resultId)
    val categoryFound: Category = Await.result(Categories.findById(resultId), databaseSessionTimeout).head

    assert(categoryFound == categoryCopy)
  }

  test("Inserting a branch in the database") {
    val branchToInsert = Branch(0, "Administracion Rural (PM)")
    val insertFut = Branches.insert(branchToInsert)
    val resultId = Await.result(insertFut, databaseSessionTimeout)

    val branchCopy = branchToInsert.copy(resultId)
    val branchFound: Branch = Await.result(Branches.findById(resultId), databaseSessionTimeout).head

    assert(branchFound == branchCopy)
  }

  test("Inserting a user in the database") {
    val anyBranch = Await.result(Branches.selectAll, databaseSessionTimeout).head
    val userToInsert = User(0, "User Test", anyBranch.id)
    val resultId = Await.result(Users.insert(userToInsert), databaseSessionTimeout)

    val userCopy = userToInsert.copy(id = resultId)
    val userFound: User = Await.result(Users.findById(resultId), databaseSessionTimeout).head

    assert(userFound == userCopy)
  }

  test("Inserting a item in the database") {
    val anyCategory = Await.result(Categories.selectAll, databaseSessionTimeout).head
    val anyUser = Await.result(Users.selectAll, databaseSessionTimeout).head
    val itemToInsert = Item(0, "13/11/2016", "PC Mac", 20000, anyCategory.id, anyUser.id)
    val resultId = Await.result(Items.insert(itemToInsert), databaseSessionTimeout)

    val itemCopy = itemToInsert.copy(resultId)
    val itemFound = Await.result(Items.findById(resultId), databaseSessionTimeout).head

    assert(itemFound == itemCopy)
  }

  test("Updating an item in the database") {
    val anyItem = Await.result(Items.selectAll, databaseSessionTimeout).head
    val itemToUpdate: Item = anyItem.copy(name = "PC Lenovo")
    Await.result(Items.update(anyItem.id, itemToUpdate), databaseSessionTimeout)

    val itemFound = Await.result(Items.findById(anyItem.id), databaseSessionTimeout).head

    assert(itemFound == itemToUpdate)
  }

  test("Updating a user in the database") {
    val anyUser = Await.result(Users.selectAll, databaseSessionTimeout).head
    val userToUpdate: User = anyUser.copy(fullName = "User Test Updated")
    Await.result(Users.update(anyUser.id, userToUpdate), databaseSessionTimeout)

    val userFound = Await.result(Users.findById(anyUser.id), databaseSessionTimeout).head

    assert(userFound == userToUpdate)
  }

  test("Deleting an item from the database") {
    val anyItem = Await.result(Items.selectAll, databaseSessionTimeout).head
    val itemToDelete = Await.result(Items.delete(anyItem.id), databaseSessionTimeout)
    val itemDeletedFound = Await.result(Items.findById(itemToDelete), databaseSessionTimeout)

    assert(itemDeletedFound match {
      case Some(s) => false
      case None => true
    })
  }

  test("On a empty Items table, insert 2 items and then select it all") {
    val itemsToInsert = List(Item(0, "13/11/2016", "PC Mac", 20000, 133, 1), Item(0, "24/03/2016", "Display 19'", 2900, 2, 1))
    itemsToInsert map(item => Items.insert(item))

    val allItems = Await.result(Items.selectAll, databaseSessionTimeout)
    allItems.foreach(println)

    assert(itemsToInsert.length == allItems.length)
  }

}
