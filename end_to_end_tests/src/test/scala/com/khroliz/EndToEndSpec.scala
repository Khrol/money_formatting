package com.khroliz

import org.scalatest._
import selenium._
import concurrent.Eventually._
import org.scalatest.time.{Millis, Seconds, Span}

class EndToEndSpec extends FunSpec with WebBrowser with Chrome with Matchers with BeforeAndAfter {
  implicit val patienceConfig =
    PatienceConfig(timeout = scaled(Span(5, Seconds)), interval = scaled(Span(500, Millis)))

  describe("Money Formatting") {
    it ("works end to end") {
      go to "http://localhost:3000"

      textField(tagName("input")).value = "1234.567"
      click on tagName("button")

      eventually {
        cssSelector("#result").element.text shouldEqual "1 234.57"
      }
    }
  }

  after {
    quit
  }
}
