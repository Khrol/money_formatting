package com.khroliz

import java.text.DecimalFormat
import java.math.RoundingMode

object Formatter {
  lazy val formatter: DecimalFormat = {
    val formatter = new DecimalFormat

    val symbols = formatter.getDecimalFormatSymbols
    symbols.setGroupingSeparator(' ')
    formatter.setDecimalFormatSymbols(symbols)

    formatter.setMaximumFractionDigits(2)
    formatter.setMinimumFractionDigits(2)
    formatter.setRoundingMode(RoundingMode.HALF_UP)
    formatter
  }

  val MAX_VALUE = 1e12
  val MIN_VALUE = -1e12

  def formatMoney(amount: Double): Either[String, String] = {
    if (amount.isNaN) {
      Left("Amount can't be NaN")
    } else if (amount.isInfinite) {
      Left("Amount can't be Infinity")
    } else if (amount > MAX_VALUE) {
      Left(s"Amount can't greater than $MAX_VALUE")
    } else if (amount < MIN_VALUE) {
      Left(s"Amount can't be less than $MIN_VALUE")
    } else {
      Right(formatter.format(amount))
    }
  }
}
