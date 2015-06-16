package models.db.common

import slick.driver.H2Driver.api.Database

abstract class AbstractDao {

  /** データベースコネクション */
  val database = Database.forConfig("h2local")

}
