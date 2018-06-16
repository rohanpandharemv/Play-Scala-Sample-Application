package controllers

import javax.inject.{Inject, Singleton}
import model.{LawyerId, DB, Person}
import play.api.libs.ws.{WSRequest, WSClient}
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json
import play.api.libs.json._
import scala.concurrent.ExecutionContext


/**
 * Created by rohanp on 2/21/18.
 */
@Singleton
class HomeController @Inject() ( ws : WSClient) (implicit context : ExecutionContext ) extends Controller {

  def index = Action {
    Ok(views.html.index("Welcome to play application"))
  }

  val personForm : Form[Person] = Form {
    mapping(
    "name" -> text
    )(Person.apply)(Person.unapply)
  }


  def addPerson = Action { implicit  request =>
    val person = personForm.bindFromRequest.get
    DB.save(person)
    Redirect(routes.HomeController.index)
  }

  def getPersons = Action {
    val persons = DB.query[Person].fetch
    Ok(Json.toJson(persons))
  }


  val lawyerIdForm : Form[LawyerId] = Form {
    mapping(
      "id" -> text
    )(LawyerId.apply)(LawyerId.unapply)
  }

  def getLawyer = Action.async {
    implicit  request =>

    val id = lawyerIdForm.bindFromRequest.get
    val url = "https://api.avvo.com/api/4/lawyers.json"

    val complexRequest : WSRequest = ws.url(url)
      .withHeaders("Authorization"->"Bearer ceen7vz351ub4huf0w3vg8yjz")
      .withQueryString("id"->id.id)

    complexRequest.get().map {
      response =>
        Ok(response.json)
    }
  }
}