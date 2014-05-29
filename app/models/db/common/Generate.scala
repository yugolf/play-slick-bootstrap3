package models.db.common

import scala.slick.driver.JdbcDriver
import scala.slick.jdbc.meta.{ MTable, createModel }

object Generate extends AbstractDao {
  def model(schema: Option[String]) = database.withSession { implicit session =>
    val tables = MTable.getTables(None, schema, None, None).list
    createModel(tables, JdbcDriver)
  }
}