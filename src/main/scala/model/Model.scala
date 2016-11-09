package model

/**
  * Created by rdsel on 31/10/2016.
  */

case class Item(id: Long, date: String, name: String, price: Double, category: Int, userAssigned: Int)
case class User(id: Int, fullName: String, branch: Int)
case class Branch(id: Int, branch: String)
case class Category(id: Int, category: String)

