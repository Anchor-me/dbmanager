package model

import play.api.libs.json.Json

/**
 * Created by mesfinmebrate on 27/06/2016.
 */
case class Example (identifier: String, name: String)

object Example {
  implicit val format = Json.format[Example]
}