package model

/**
  * Created by rdsel on 31/10/2016.
  */

case class Item(id: Long, date: String, name: String, price: Double, category: Long, userAssigned: Long)
case class User(id: Long, fullName: String, branch: Long)
case class Branch(id: Long, branch: String)
case class Category(id: Long, category: String)

