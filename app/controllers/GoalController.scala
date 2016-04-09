package controllers

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import model.{GoalDao}
import com.anchor.model._
import com.anchor.json._

class GoalController @Inject() (goalDao: GoalDao) extends Controller {

  def get(goalId: String) = Action.async {
    goalDao.getGoalById(goalId) map {
      case None => NotFound
      case Some(goal) => Ok(Json.toJson(goal))
    }
  }

  def index() = Action.async {
    goalDao.indexGoal(cannedGoal) map { _ => Ok("Goal indexed") }
  }

  val cannedGoal = Goal (
    id = Id("1001"),
    themeId = Id("2002"),
    summary = "Create Anchor.me",
    description = "Use all your knowledge and skills to create a life-management system",
    level = 0,
    priority = true
  )
}