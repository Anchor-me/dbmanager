package model

import javax.inject.{Named, Inject}
import scala.concurrent.{ExecutionContext, Future}
import com.sksamuel.elastic4s.{IndexAndType, ElasticDsl}
import com.evojam.play.elastic4s.configuration.ClusterSetup
import com.evojam.play.elastic4s.{PlayElasticFactory, PlayElasticJsonSupport}
import com.anchor.model.Goal
import com.anchor.json._

class GoalDao @Inject()(cs: ClusterSetup, elasticFactory: PlayElasticFactory, @Named("goal") indexAndType: IndexAndType)
    extends ElasticDsl with PlayElasticJsonSupport {

  private[this] lazy val client = elasticFactory(cs)

  def getGoalById(goalId: String)(implicit ec: ExecutionContext): Future[Option[Goal]] = client execute {
    get id goalId from indexAndType
  } map (_.as[Goal])

  def indexGoal(goal: Goal) = client execute {
    index into indexAndType source goal id goal.id.id
  }
}