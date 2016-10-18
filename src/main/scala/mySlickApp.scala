package db
import slick.driver.MySQLDriver.api._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.duration.Duration

/**
  * Created by rdsel on 15/10/2016.
  */
object mySlickApp extends App with DAO {

  println("DataBase db.Items")

  val db = Database.forConfig("mysqldb")
  val items = TableQuery[Items]

  try {
    // Create the database schema
    val setup = DBIO.seq(
      items.schema.create,

      items += (100, 2016, "Lenovo M73", 11000, 1, 12),
      items += (101, 2016, "Pendrive", 95, 1, 3)
    )
    // Database connections and transactions are managed automatically by Slick. Push the Data into the DB
//    db.run(setup)

    val setupFuture =  Future {
      db.run(setup)
    }

    //once our DB has finished initializing we are ready to roll !
    //NOTE: Await does not block here!
    Await.result(setupFuture, Duration.Inf).andThen{
      case Success(_) => "Connected..."
      case Failure(err) => println(err);
    }


    // Read all coffees and print them to the console
    println("db.Items:")
//    db.run(items.result.map(println))

    val q1 = for(i <- items)
      yield (i.id, i.name, i.price, i.category, i.userAssigned)

    db.stream(q1.result).foreach(println)

  } finally db.close


}
