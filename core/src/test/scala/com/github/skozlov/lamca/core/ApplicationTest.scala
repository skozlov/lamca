package com.github.skozlov.lamca.core

import org.scalatest.{FlatSpec, Matchers}

class ApplicationTest extends FlatSpec with Matchers{
	"Application" should "be left-associative" in {
		val term1 = Variable("x")
		val term2 = Variable("y")
		val term3 = Variable("z")
		val term4 = Variable("a")
		Application(term1, term2, term3, term4) shouldBe Application(Application(Application(term1, term2), term3), term4)
	}
}