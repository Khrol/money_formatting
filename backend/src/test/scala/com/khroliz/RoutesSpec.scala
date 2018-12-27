package com.khroliz

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FunSpec, Matchers}

class RoutesSpec extends FunSpec with Matchers with ScalatestRouteTest {
  import com.khroliz.Routes.route

  describe("Routes") {
    it ("returns 404 error for non-existing paths") {
      Get() ~> Route.seal(route) ~> check {
        status shouldEqual StatusCodes.NotFound
      }
    }

    describe("/format") {
      it ("returns value for double input") {
        Get("/format?amount=12456.156") ~> route ~> check {
          status shouldEqual StatusCodes.OK
          responseAs[String] shouldEqual "12 456.16"
        }
      }

      it ("rejects incorrect inputs") {
        Get("/format?amount=bla-bla-bla") ~> Route.seal(route) ~> check {
          status shouldEqual StatusCodes.BadRequest
          responseAs[String] shouldEqual
            """The query parameter 'amount' was malformed:
              |'bla-bla-bla' is not a valid 64-bit floating point value""".stripMargin
        }
      }

      it ("parses huge amounts") {
        Get("/format?amount=1234123412341234123412341234") ~> Route.seal(route) ~> check {
          status shouldEqual StatusCodes.BadRequest
          responseAs[String] shouldEqual "Amount can't greater than 1.0E12"
        }
      }
    }
  }

}
