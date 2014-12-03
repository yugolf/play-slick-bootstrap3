package controllers

import play.api._
import play.api.mvc._
import models.db.common.Generate
import scala.slick.model.codegen.SourceCodeGenerator
import play.api.data._
import play.api.data.Forms._

case class GenerateForms(slickDriver: String, outputFolder: String, pkg: String, schema: Option[String])
case class UisampleForms(textfield: String, selectfield: String, radiofield: String, datefield: String, filefield: String, passwordfield: String)

object Application extends Controller {

  val generateForm = Form(
    mapping(
      "slickDriver"  -> nonEmptyText(maxLength = 100),
      "outputFolder" -> nonEmptyText(maxLength = 100),
      "pkg"          -> nonEmptyText(maxLength = 100),
      "schema"       -> optional(text(maxLength = 100)))(GenerateForms.apply)(GenerateForms.unapply))

  def index = Action {
    val form = GenerateForms("scala.slick.driver.H2Driver", "app", "models.db.common", Some("PUBLIC"))
    Ok(views.html.generate(generateForm.fill(form)))
  }

  def generate = Action { implicit request =>
    generateForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.generate(form))
      },
      success = { form =>
        val model = Generate.model(form.schema)
        new SourceCodeGenerator(model).writeToFile(form.slickDriver, form.outputFolder, form.pkg)
        Ok(views.html.generate(generateForm.bindFromRequest))
      })
  }

  val uisampleForm = Form(
    mapping(
      "textfield"     -> nonEmptyText(maxLength = 100),
      "selectfield"   -> nonEmptyText(maxLength = 100),
      "radiofield"    -> nonEmptyText(maxLength = 100),
      "datefield"     -> nonEmptyText(maxLength = 100),
      "filefield"     -> nonEmptyText(maxLength = 100),
      "passwordfield" -> nonEmptyText(maxLength = 100))(UisampleForms.apply)(UisampleForms.unapply))

  def uisample = Action {
    Ok(views.html.uisample(uisampleForm.fill(null)))
  }
}