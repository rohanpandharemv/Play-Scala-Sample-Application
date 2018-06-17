package model

import play.api.libs.json._
/**
 * Created by rohanp on 2/19/18.
 */
case class Person(name : String)

object Person {
  implicit val personFormat = Json.format[Person]
}