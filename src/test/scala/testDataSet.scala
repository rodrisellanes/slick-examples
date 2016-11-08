//import db._
//import model.Branch
//import slick.driver.MySQLDriver.api._
//
///**
//  * Created by rdsel on 30/10/2016.
//  */
//object testDataSet {
//
//  val insertData = DBIO.seq(
//
//    Categories ++= Seq(
//      (0, "Hardware"),
//      (0, "Software"),
//      (0, "Services")
//    ),
//
//    Branches ++= Seq(
//      Branch(0, "Administracion Rural (PM)"),
//      Branch(0, "Centro de Genetica"),
//      Branch(0, "Las Lilas"),
//      Branch(0, "El Pastor"),
//      Branch(0, "La Josefina"),
//      Branch(0, "Pichu Co"),
//      Branch(0, "La Leonor")
//    ),
//
//    Users ++= Seq(
//      (0, "Rodrigo Sellanes", 1),
//      (0, "Jhone Freys", 2),
//      (0, "Michael Weedny", 7),
//      (0, "Martin Osk", 1)
//    )
//  )
//
//}
