package controllers

import play.api.libs.json.Json
import play.api.mvc.Results
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}


class RideControllerSpec extends PlaySpecification with Results {

  "RideController" should {

    "GET /rides" in {
      "return OK" in new WithApplication() {
        val request = FakeRequest(GET, "/rides")
        val Some(result) = route(app, request)
        status(result) mustEqual OK
      }
    }

    "POST /rides" in {
      "create and notify a ride" in new WithApplication() {
        val request = FakeRequest(POST, "/rides").withBody(Json.obj(
          "category" -> "pop",
          "driver" -> "Pedro",
          "passenger" -> "Flavia",
          "amount" -> 70.99))
        val Some(result) = route(app, request)
        status(result) mustEqual CREATED
      }
    }
  }
}