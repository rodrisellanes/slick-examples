package db
import slick.driver.MySQLDriver.api._

import mySlickApp._

/**
  * Created by rdsel on 30/10/2016.
  */
object insertStandardData {

    val insertData = DBIO.seq(

      categories ++= Seq(
        (0, "Hardware"),
        (0, "Software"),
        (0, "Services")
      ),

      branches ++= Seq(
        (0, "Administracion Rural (PM)"),
        (0, "Centro de Genetica"),
        (0, "Las Lilas"),
        (0, "El Pastor"),
        (0, "La Josefina"),
        (0, "Pichu Co"),
        (0, "La Leonor")
      ),

      users ++= Seq(
        (0, "Rodrigo Sellanes", 1),
        (0, "Jhone Freys", 2),
        (0, "Michael Weedny", 7),
        (0, "Martin Osk", 1)
      )
    )
    db.run(insertData)

}
