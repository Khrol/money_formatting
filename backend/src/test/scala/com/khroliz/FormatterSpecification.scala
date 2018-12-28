package com.khroliz

import org.scalacheck._
import org.scalacheck.Prop.{forAll, BooleanOperators}

class FormatterSpecification extends Properties("Formatter") {
  property("formats well for allowed range") = forAll { n: Double =>
    (n >= -1e12 && n <= 1e12) ==> (Formatter.formatMoney(n) match {
      case Right(value) => "^[-\\s0-9.]*$".r.findFirstIn(value).nonEmpty
      case Left(_) => false
    })
  }
}
