package models.db.common

import models.db.common.Tables.profile.simple._
import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.H2Driver.simple._

abstract class AbstractDao {

  /** データベースコネクション */
  val database = Database.forDataSource(DB.getDataSource())

}
