import db.{Branches, Categories, Items, Users}
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

    val userCpoy = userToInsert.copy(resultId)
    val userFound: User = Await.result(Users.findById(resultId), databaseSessionTimeout).head

    assert(userFound == userCpoy)
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

}
