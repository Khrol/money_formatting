package com.khroliz

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.`Access-Control-Allow-Origin`
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route


object Routes {
  val route: Route =
    path("format") {
      respondWithHeaders(`Access-Control-Allow-Origin`.*) {
        parameters("amount".as[Double]) {
          amount =>
            Formatter.formatMoney(amount) match {
              case Right(result) => complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, result))
              case Left(message) => complete(HttpResponse(StatusCodes.BadRequest, entity = message))
            }
        }
      }
    }
}
