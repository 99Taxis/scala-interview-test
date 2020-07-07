package controllers

import controllers.Category.Category
import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}

object Category extends Enumeration {
  type Category = Value
  val pop, taxi, food = Value
}

class Ride {
  var category: Category = null
  var driver: String = null
  var passenger: String = null
  var amount: BigDecimal = null
  var fee: BigDecimal = null
}

class RideService() {
  def generateRide(category: String, driver: String, passenger: String, amount: BigDecimal): Ride = {
    val ride = new Ride()
    ride.category = Category.withName(category)
    ride.driver = driver
    ride.passenger = passenger
    ride.amount = amount
    ride
  }

  def calculateFee(ride: Ride) = {
    if (ride.category.equals(Category.pop)) {
      ride.fee = ride.amount * 0.1
    }
    if (ride.category.equals(Category.taxi)) {
      ride.fee = ride.amount * 0.5
    }
  }

  def notifyRideCreated(ride: Ride): Unit = {
    // Simulates call to another application
    Thread.sleep(10000)
    if (ride.fee != null) {
      println("Ride notified!")
      println("Fee notified!")
    } else {
      println("Ride notified!")
    }
  }
}

@Singleton
class RideController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index = Action { implicit request: Request[AnyContent] => Ok }

  def create() = Action(parse.json) { implicit request: Request[JsValue] =>
    var category = (request.body \ "category").as[String]
    var driver = (request.body \ "driver").as[String]
    var passenger = (request.body \ "passenger").as[String]
    var amount = (request.body \ "amount").as[BigDecimal]

    var rideService = new RideService()
    var ride = rideService.generateRide(category, driver, passenger, amount)
    rideService.calculateFee(ride)
    rideService.notifyRideCreated(ride)

    var json = Json.obj(
      "category" -> ride.category,
      "driver" -> ride.driver,
      "passenger" -> ride.passenger,
      "amount" -> ride.amount,
      "fee" -> ride.fee
    )
    Created(json)
  }
}
