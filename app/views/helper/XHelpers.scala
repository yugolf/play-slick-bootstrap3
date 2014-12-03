import play.api.templates._
import scala.language.implicitConversions
import scala.collection.JavaConverters._

package views.html.helper {

  object HtmlUtil {
    def toFormControl(elements: FieldElements): String = {
      val inputOptions = elements.args.get('_opts)
      val target = elements.input.body.replace("/>", inputOptions.getOrElse("") + "/>")
      val beginTag = target.indexOf("<")
      val endTag = target.indexOf("/>", beginTag + 1)
      val targetStr = target.substring(0, endTag + 1)
      val classstr = "class=\""

      val beginIndex = targetStr.indexOf(classstr)
      if (beginIndex > 0) {
        val endIndex = targetStr.indexOf("\"", beginIndex + classstr.length)
        if (endIndex > 0) {
          val classStr = targetStr.substring(beginIndex + classstr.length, endIndex)
          if (!classStr.contains("form-control") && !classStr.contains("buttonset")) {
            target.substring(0, endIndex) + " form-control" + target.substring(endIndex)
          } else target
        } else target
      } else {
        if (endTag > 0) {
          target.substring(0, endTag) + " class=\"form-control\"" + target.substring(endTag)
        } else target
      }
    }
  }

}