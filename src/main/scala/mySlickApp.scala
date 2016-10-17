import slick.driver.MySQLDriver.api._

import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
  * Created by rdsel on 15/10/2016.
  */
object mySlickApp extends App {

  println("DataBase Items")

  val db = Database.forConfig("mysqldb")
  val items = TableQuery[Items]

  try {
    // Create the database schema
    val setup = DBIO.seq(
      items.schema.create,

      items += (100, "Lenovo M73", 11000, "Hardware", "Rodrigo Sellanes"),
      items += (101, "Pendrive", 95, "Hardware", "Rodrigo Sellanes")
    )
    // Database connections and transactions are managed automatically by Slick. Push the Data into the DB
    db.run(setup)


    // Read all coffees and print them to the console
    println("Items:")
//    db.run(items.result.map(println))

    val q1 = for(i <- items)
      yield (i.id, i.name, i.price, i.category, i.userAssigned)

    db.stream(q1.result).foreach(println)

  } finally db.close


}
