package model

import play.api.libs.json.Json

/**
 * Created by rohanp on 2/21/18.
 */
case class LawyerId (
                    id : String
                    )

object LawyerId {
  implicit  val lawyerFormat = Json.format[LawyerId]
}
