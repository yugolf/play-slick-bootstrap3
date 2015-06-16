package models.db.common

import slick.jdbc.meta.MTable
import slick.driver.H2Driver
import slick.driver.MySQLDriver
import slick.driver.PostgresDriver
import slick.driver.DerbyDriver
import slick.driver.HsqldbDriver
import slick.driver.SQLiteDriver
import scala.concurrent.ExecutionContext.Implicits.global

object Generate extends AbstractDao {
  def model(driver: String, schema: Option[String]) = {
    val tables = MTable.getTables(None, schema, None, None)
    val modelAction = driver match {
      case "slick.driver.H2Driver" => H2Driver.createModel(Some(tables))
      case "slick.driver.MySQLDriver" => MySQLDriver.createModel(Some(tables))
      case "slick.driver.PostgresDriver" => PostgresDriver.createModel(Some(tables))
      case "slick.driver.DerbyDriver" => DerbyDriver.createModel(Some(tables))
      case "slick.driver.HsqldbDriver" => HsqldbDriver.createModel(Some(tables))
      case "slick.driver.SQLiteDriver" => SQLiteDriver.createModel(Some(tables))
      case _ => H2Driver.createModel(Some(tables))
    }

    database.run(modelAction)
  }
}