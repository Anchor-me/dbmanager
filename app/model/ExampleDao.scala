package model

import javax.inject.{Named, Inject}
import com.evojam.play.elastic4s.{PlayElasticJsonSupport, PlayElasticFactory}
import com.evojam.play.elastic4s.configuration.ClusterSetup
import com.sksamuel.elastic4s.{ElasticDsl, IndexAndType}
import scala.concurrent.{Future, ExecutionContext}

class ExampleDao @Inject()(cs: ClusterSetup, elasticFactory: PlayElasticFactory, @Named("example") indexAndType: IndexAndType)
  extends ElasticDsl with PlayElasticJsonSupport {

  private[this] lazy val client = elasticFactory(cs)

  def getExampleById(exampleId: String)(implicit ec: ExecutionContext): Future[Option[Example]] = client execute {
    get id exampleId from indexAndType
  } map (_.as[Example])

  def indexGoal(example: Example) = client execute {
    index into indexAndType source example id example.identifier
  }
}