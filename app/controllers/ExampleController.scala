package controllers

import javax.inject.Inject
import model.{Example, ExampleDao}
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class ExampleController @Inject() (exampleDao: ExampleDao) extends Controller {

  def get(exampleId: String) = Action.async {
    exampleDao.getExampleById(exampleId) map {
      case None => NotFound
      case Some(goal) => Ok(Json.toJson(goal))
    }
  }

  def index() = Action.async {
    exampleDao.indexGoal(cannedExample) map { _ => Ok(s"Example ${cannedExample.identifier} indexed") }
  }

  def add = Action.async { request =>
    val json = request.body.asJson.get
    val example = json.as[Example]
    exampleDao.indexGoal(example) map { _ => Ok(s"Example ${example.identifier} indexed") }
  }

  val cannedExample = Example (
    identifier = "1234",
    name = "Tyrion"
  )
}
