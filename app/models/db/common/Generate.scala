package models.db.common

import slick.driver.JdbcDriver
import slick.jdbc.meta.MTable
import slick.driver.H2Driver
import scala.concurrent.ExecutionContext.Implicits.global

object Generate extends AbstractDao {
  def model(schema: Option[String]) = database.withSession { implicit session =>
    val tables = MTable.getTables(None, schema, None, None)

    val modelAction  = H2Driver.createModel(Some(tables))
    database.run(modelAction)
  }
}