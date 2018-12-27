package com.khroliz

import java.math.MathContext

import org.scalatest.{FunSpec, Matchers}

class FormatterSpec extends FunSpec with Matchers {
  describe("Formatter") {
    describe("formatMoney") {
      it ("converts double to string with two digits decimal part") {
        Formatter.formatMoney(10.0) shouldEqual Right("10.00")
      }

      it ("rounds decimal part HALF_UP") {
        Formatter.formatMoney(10.1249) shouldEqual Right("10.12")
        Formatter.formatMoney(10.125) shouldEqual Right("10.13")
        Formatter.formatMoney(10.125001) shouldEqual Right("10.13")
      }

      it ("groups integer part by three separating by spaces") {
        Formatter.formatMoney(12345.123) should equal(Right("12 345.12"))
        Formatter.formatMoney(1234567.123) should equal(Right("1 234 567.12"))
      }

      it ("works for negative numbers") {
        Formatter.formatMoney(-12345.123) should equal(Right("-12 345.12"))
      }

      it ("rounds HALF_UP for negative numbers as well") {
        Formatter.formatMoney(-12345.1249) shouldEqual Right("-12 345.12")
        Formatter.formatMoney(-12345.125) shouldEqual Right("-12 345.13")
        Formatter.formatMoney(-12345.12501) shouldEqual Right("-12 345.13")
      }

      it ("works for maximum allowed number 1e12") {
        Formatter.formatMoney(1e12) shouldEqual Right("1 000 000 000 000.00")
      }

      it ("works for big numbers") {
        Formatter.formatMoney(12345678901.125) shouldEqual Right("12 345 678 901.13")
      }

      it ("works for small numbers") {
        Formatter.formatMoney(-12345678901.125) shouldEqual Right("-12 345 678 901.13")
      }

      it ("works for minimum allowed number -1e12") {
        Formatter.formatMoney(-1e12) shouldEqual Right("-1 000 000 000 000.00")
      }

      it ("Raises an exception for values above upper border") {
        Formatter.formatMoney(1000000000000.01D) shouldEqual Left("Amount can't greater than 1.0E12")
      }

      it ("Returns an error for values below low border") {
        Formatter.formatMoney(-1000000000000.01D) shouldEqual Left("Amount can't be less than -1.0E12")
      }

      it ("works for zero") {
        Formatter.formatMoney(0) shouldEqual Right("0.00")
      }

      it ("works for very small positive number") {
        Formatter.formatMoney(Double.MinPositiveValue) shouldEqual Right("0.00")
      }

      it ("raises an exception for NaN number") {
        Formatter.formatMoney(Double.NaN) shouldEqual Left("Amount can't be NaN")
      }

      it ("raises an exception for Infinity number") {
        List(Double.PositiveInfinity, Double.NegativeInfinity).foreach { value =>
          Formatter.formatMoney(value) shouldEqual Left("Amount can't be Infinity")
        }
      }
    }
  }
}
